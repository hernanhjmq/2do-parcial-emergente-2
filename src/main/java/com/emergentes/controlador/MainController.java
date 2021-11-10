
package com.emergentes.controlador;

import com.emergentes.modelo.DAO_Seminario;
import com.emergentes.utiles.ConexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
            String op;
            op = (request.getParameter("op")!= null) ? request.getParameter("op"):"list";
                System.out.println("la opcion es: "+op);
  
                PreparedStatement ps;
                ConexionBD canal = new ConexionBD();
                Connection conn = canal.conectar();
                ArrayList<DAO_Seminario> lista = new ArrayList<DAO_Seminario>();
                ResultSet rs;
                if(op.equals("list"))
                {
                    String sql = "select * from seminarios";
                    try {
                    ps = conn.prepareStatement(sql);
                    rs =  ps.executeQuery();
                    
                    while(rs.next())
                    {
                        DAO_Seminario lib = new DAO_Seminario();
                        lib.setId(rs.getInt("id"));
                        lib.setTitulo(rs.getString("titulo"));
                        lib.setExpositor(rs.getString("expositor"));
                        lib.setFecha(rs.getString("fecha"));
                        lib.setHoras(rs.getString("horas"));
                        lib.setCupos(rs.getInt("cupos"));
                        lista.add(lib);
                        
                    }
                    request.setAttribute("lista", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                     } catch (SQLException ex) {
                         Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null, ex);
                    }
                }
                 int id;
                if(op.equals("nuevo"))
                {
          

                    DAO_Seminario semi = new DAO_Seminario();
                    request.setAttribute("semi", semi);
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                  
                }
                 if(op.equals("editar"))
                {
                    id  = Integer.parseInt(request.getParameter("id"));
                  
                    try {
                        
                          DAO_Seminario sem1 = new DAO_Seminario();
                       
                        String sql = "select * from seminarios where id= ?";
                        ps = conn.prepareStatement(sql);
                        ps.setInt(1, id);
                        rs = ps.executeQuery();
                        if(rs.next())
                        {
                            sem1.setId(rs.getInt("id"));
                            sem1.setTitulo(rs.getString("titulo"));
                            sem1.setExpositor(rs.getString("expositor"));
                            sem1.setFecha(rs.getString("fecha"));
                            sem1.setHoras(rs.getString("horas"));
                            sem1.setCupos(rs.getInt("cupos"));
                        }
                        request.setAttribute("semi", sem1);
                        request.getRequestDispatcher("editar.jsp").forward(request, response);
                     } catch (SQLException ex) {
                         Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null, ex);
                    }
                    
                }
                if(op.equals("eliminar"))
                {
                    id  = Integer.parseInt(request.getParameter("id"));
                    try {
                    String sql  = "delete from seminarios where id = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1, id);
                    ps.executeUpdate();
                    response.sendRedirect("MainController");
                     } catch (SQLException ex) {
                         Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null, ex);
                    }
                }
                
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
                try {
            
       
                    int id = Integer.parseInt(request.getParameter("id"));
                     DAO_Seminario sem = new DAO_Seminario();
                     
                    String titulo = request.getParameter("titulo");
                    String expositor = request.getParameter("expositor");
                    String fecha = request.getParameter("fecha");
                    String horas = request.getParameter("horas");
                    int cupos  = Integer.parseInt(request.getParameter("cupos"));
                    sem.setId(id);
                    sem.setTitulo(titulo);
                    sem.setExpositor(expositor);
                    sem.setFecha(fecha);
                    sem.setHoras(horas);
                    sem.setCupos(cupos);
                            
                     
                    ConexionBD canal = new ConexionBD();
                    Connection conn = canal.conectar();
                PreparedStatement ps;
                ResultSet rs;
                if(id == 0)
                {
                  
                    String sql = "INSERT INTO seminarios  (titulo,expositor,fecha,horas,cupos) VALUES (?,?,?,?,?)";
                    try {
                        ps = conn.prepareStatement(sql);
                    ps.setString(1, sem.getTitulo());
                    ps.setString(2, sem.getExpositor());
                    ps.setString(3, sem.getFecha());
                    ps.setString(4, sem.getHoras());
                    ps.setInt(5, sem.getCupos());
                     ps.executeUpdate();
                    } catch (Exception e) {
                    }
                   

                }
                else
                {
                    String sql2  = "update seminarios set titulo= ?, expositor= ?, fecha= ?, horas= ?, cupos = ? where id = ?";
                    try {
                       ps = conn.prepareStatement(sql2);
                       ps.setString(1, sem.getTitulo());
                       ps.setString(2, sem.getExpositor());
                       ps.setString(3, sem.getFecha());
                       ps.setString(4, sem.getHoras());
                       ps.setInt(5, sem.getCupos());
                       ps.executeUpdate(); 
                   }catch (Exception ex) 
                {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null, ex);
                }
                }
              
                }catch (Exception ex) 
                {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null, ex);
                }
                
        response.sendRedirect("MainController");
        
    }
}
