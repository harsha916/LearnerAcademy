package com.management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.management.model.Batch;
import com.management.model.Subject;
/**
 * @author Suyash verma
 *
 */
public class BatchDAO {
	public static int getBatchId(Connection connection) {
		int batchID = 0;
		try{
			PreparedStatement ps=connection.prepareStatement("SELECT MAX( id ) AS last_id FROM batch1");
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				batchID = rs.getInt("last_id");
				batchID++;
			}
		}catch(Exception e){System.out.println(e);}
		return batchID;
	}
	
	public int extractBatchDetails() throws ClassNotFoundException, SQLException{
		String extract_batch_details = "SELECT * FROM batch1";
		
		Connection connection = DBConnection.createConnection();
		PreparedStatement ps=connection.prepareStatement(extract_batch_details);
		ResultSet rs=ps.executeQuery();
		Batch array[] = new Batch[8];
		int i = 0;
	
		while(rs.next()){
			array[i].createBatchID(rs.getInt(i));
			Subject subs[] = new Subject[8];
			for(int j=0;j<8;j++) {
				subs[j].setSubjectID(rs.getInt(j));
				subs[j].setName(subs[j].getName());
			}
		}
		return i;
	}
	
	public int createBatch(Batch batch) throws ClassNotFoundException, SQLException{
		String insert_batch = "INSERT INTO batch1" + 
	    " (batch_id,subject_1,subject_2,subject_3,subject_4,subject_5,subject_6,subject_7,subject_8)"+
        " VALUES (?,?,?,?,?,?,?,?,?)";
		
		int result = 0;
		Connection connection = DBConnection.createConnection();
		PreparedStatement ps = connection.prepareStatement(insert_batch);
		final int[] sid = batch.getSubjectIDs();
		ps.setInt(1,getBatchId(connection));
		for(int i=0;i<8;i++) {
			int j = 2;
			if(sid[i] != -1) {
				ps.setInt(j, i);
			}else {
				ps.setInt(j,0);
			}
			j++;
		}
		
		System.out.println("preparedStatement");
		//executing query
		result = ps.executeUpdate();
		connection.close();
		return result;
	}
	
	public int deleteBatch(int batchID) throws ClassNotFoundException, SQLException
	{
		String insert_batch = "DELETE FROM batch1 WHERE batch_id = " + batchID;
				
		int result = 0;
		Connection connection = DBConnection.createConnection();
		PreparedStatement ps = connection.prepareStatement(insert_batch);
				
		System.out.println("preparedStatement");
		//executing query
		result = ps.executeUpdate();
		connection.close();
		return result;
	}
}
