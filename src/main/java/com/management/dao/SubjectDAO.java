package com.management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.management.model.Subject;
/**
 * @author Suyash verma
 *
 */
public class SubjectDAO {
	public static int getSubjectId(Connection connection) {
		int id=0;
		try{
			PreparedStatement ps=connection.prepareStatement("SELECT MAX( id ) AS last_id FROM subject");
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				id=rs.getInt("last_id");
				id++;
			}
		}catch(Exception e){System.out.println(e);}
		return id;
	}
	public int addSubject(Subject sub) throws ClassNotFoundException, SQLException{
		String insert_emp_query = "INSERT INTO subject (subject_id,name,teacher_name)" +
			                      " VALUES (?,?,?)";
		int result = 0;
		Connection connection = DBConnection.createConnection();
		PreparedStatement ps = connection.prepareStatement(insert_emp_query);
		ps.setInt(1,getSubjectId(connection));
		ps.setString(2, sub.getName());
		ps.setString(3, sub.getTeacherName());
	
		System.out.println("preparedStatement");
		//executing query
		result = ps.executeUpdate();
		return result;
	}
	
	public int deleteSubject(String sub_id) throws ClassNotFoundException, SQLException{
		String delete_subject = "DELETE FROM subject WHERE subject_id = " + sub_id;
		int result = 0;
		Connection connection = DBConnection.createConnection();
		PreparedStatement ps = connection.prepareStatement(delete_subject);
		System.out.println("preparedStatement");
		//executing query
		result = ps.executeUpdate();
		return result;
	}
}
