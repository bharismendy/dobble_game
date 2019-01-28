package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.Properties;

public class personnal_setting {
// goal : create personnal settings for the user
	private int Timer;
	private int nb_symbole_par_carte;
	private int nb_symbole;
	private int nb_carte;
	private int nb_variantes;
	private InputStream inputStream;
	private FileOutputStream outputStream;
	private String propFileName = "setting.properties";

	public personnal_setting() {
		try {
			Timer = getPropValues("timer");
			nb_carte = getPropValues("nb_carte");
			nb_symbole = getPropValues("nb_symbole");
			nb_symbole_par_carte = getPropValues("nb_symbole_par_carte");
			nb_variantes = getPropValues("nb_variantes");
		} catch (IOException e) {
			//set default setting in case of problem
			Timer = 15;
			nb_carte = 20;
			nb_symbole = 25;
			nb_symbole_par_carte = 5;
			nb_variantes = 5;
			e.printStackTrace();
		}
	}

	public void default_value(){
		this.setTimer(15);
		this.setNb_carte(20);
		this.setNb_symbole(25);
		this.setNb_variantes(5);
		this.setNb_symbole_par_carte(5);
	}

	private int getPropValues(String name_value) throws IOException {
		String get_result = "0";
		try {
			Properties prop = new Properties();

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			get_result = prop.getProperty(name_value);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return Integer.parseInt(get_result);
	}

	private void setPropValues(String name_value,int value) throws IOException {
		try {
			Properties prop = new Properties();
			outputStream = new FileOutputStream("settings/"+propFileName);
			switch (name_value) {
			case "timer":
				prop.setProperty(name_value, Integer.toString(value));
				prop.setProperty("nb_carte", Integer.toString(this.getNb_carte()));
				prop.setProperty("nb_symbole_par_carte", Integer.toString(this.getNb_symbole_par_carte()));
				prop.setProperty("nb_variantes", Integer.toString(this.getNb_variantes()));
				prop.setProperty("nb_symbole", Integer.toString(this.getNb_symbole()));
				break;
			case "nb_carte":
				prop.setProperty(name_value, Integer.toString(value));
				prop.setProperty("timer", Integer.toString(this.getTimer()));
				prop.setProperty("nb_symbole_par_carte", Integer.toString(this.getNb_symbole_par_carte()));
				prop.setProperty("nb_variantes", Integer.toString(this.getNb_variantes()));
				prop.setProperty("nb_symbole", Integer.toString(this.getNb_symbole()));
				break;
			case "nb_symbole_par_carte":
				prop.setProperty(name_value, Integer.toString(value));
				prop.setProperty("timer", Integer.toString(this.getTimer()));
				prop.setProperty("nb_carte", Integer.toString(this.getNb_carte()));
				prop.setProperty("nb_variantes", Integer.toString(this.getNb_variantes()));
				prop.setProperty("nb_symbole", Integer.toString(this.getNb_symbole()));
				break;
			case "nb_variantes":
				prop.setProperty(name_value, Integer.toString(value));
				prop.setProperty("timer", Integer.toString(this.getTimer()));
				prop.setProperty("nb_carte", Integer.toString(this.getNb_carte()));
				prop.setProperty("nb_symbole_par_carte", Integer.toString(this.getNb_symbole_par_carte()));
				prop.setProperty("nb_symbole", Integer.toString(this.getNb_symbole()));
				break;
			case "nb_symbole":
				prop.setProperty(name_value, Integer.toString(value));
				prop.setProperty("timer", Integer.toString(this.getTimer()));
				prop.setProperty("nb_carte", Integer.toString(this.getNb_carte()));
				prop.setProperty("nb_symbole_par_carte", Integer.toString(this.getNb_symbole_par_carte()));
				prop.setProperty("nb_variantes", Integer.toString(this.getNb_variantes()));
				break;
			default:
				break;
			}
			prop.store(outputStream,null);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public Boolean recreate_card() {
		Boolean result = true;
	    String[] command = { "sh","ressources/minizinc/generate.sh",Integer.toString(this.getNb_carte()),Integer.toString(this.getNb_symbole()),Integer.toString(this.getNb_symbole_par_carte()),Integer.toString(this.getNb_variantes())};
	    Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	String fileName = "ressources/minizinc/result.txt";
	    try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            if((line == null || line.contains("=====UNSATISFIABLE====="))){
            	result = false;
            }
            // Always close files.
            bufferedReader.close();
            fileReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" +fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '"+fileName+"'");
        }
        try {
        	OutputStream os = new FileOutputStream("ressources/paquet.json");
			Files.copy(new File(fileName).toPath(), os);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return result;
	}
	public int getTimer() {
		return Timer;
	}
	public void setTimer(int timer) {
		Timer = timer;
		try {
			setPropValues("timer", Timer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getNb_symbole_par_carte() {
		return nb_symbole_par_carte;
	}
	public void setNb_symbole_par_carte(int nb_symbole_par_carte) {
		this.nb_symbole_par_carte = nb_symbole_par_carte;
		try {
			//must set all value
			setPropValues("nb_symbole_par_carte", nb_symbole_par_carte);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getNb_carte() {
		return nb_carte;
	}
	public void setNb_carte(int nb_carte) {
		this.nb_carte = nb_carte;
		try {
			setPropValues("nb_carte", nb_carte);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getNb_symbole() {
		return nb_symbole;
	}
	public void setNb_symbole(int nb_symbole) {
		this.nb_symbole = nb_symbole;
		try {
			setPropValues("nb_symbole", nb_symbole);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setNb_variantes(int nb_variantes) {
		this.nb_variantes = nb_variantes;
		try {
			setPropValues("nb_variantes", nb_variantes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getNb_variantes() {
		return nb_variantes;
	}
}
