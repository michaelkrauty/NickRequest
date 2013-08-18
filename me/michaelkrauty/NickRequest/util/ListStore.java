package me.michaelkrauty.NickRequest.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;

public class ListStore {
	
	private File storageFile;
	private ArrayList<String> values;
	
	public ListStore(File file){
		this.storageFile = file;
		this.values = new ArrayList<String>();
		
		if (this.storageFile.exists() == false){
			try{
				this.storageFile.createNewFile();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void load(){
		try{
			DataInputStream input = new DataInputStream(new FileInputStream(this.storageFile));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			String line;
			
			while ((line = reader.readLine()) != null){
				if (this.values.contains(line) == false){
					this.values.add(line);
				}
			}
			
			reader.close();
			input.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void save(){
		try{
			FileWriter stream = new FileWriter(this.storageFile);
			BufferedWriter out = new BufferedWriter(stream);
			
			for (String value : this.values){
				out.write(value);;
				out.newLine();
			}
			
			out.close();
			stream.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
}
