package br.ufscar.rcms.servico.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class RunScriptLattes implements Runnable {

	private String hash;
	
    private String pastaScriptLates;
	
	public RunScriptLattes(String hash, String pastaScriptLates) {
		super();
		this.hash = hash;
		this.pastaScriptLates = pastaScriptLates;
	}

	@Override
	public void run() {
		try {
			final ArrayList<String> commands = new ArrayList<String>();
			commands.add("/bin/bash");
			commands.add(pastaScriptLates + "runScriptLattes.sh");
			commands.add(hash + ".config");

			ProcessBuilder pb = new ProcessBuilder(commands);
			pb.directory(new File(pastaScriptLates));

			pb.redirectErrorStream(true);

			Process proc = pb.start();

			proc.getOutputStream().close();

			InputStream inputstream = proc.getInputStream();
	        InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
	        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
	        
	        proc.waitFor();
	        
	        String line;
	        StringBuilder output = new StringBuilder();
	        while ((line = bufferedreader.readLine()) != null) {
	            output.append(line);
	        }
	        System.out.println(output.toString());
	        inputstream.close();

			proc.destroy();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getPastaScriptLates() {
		return pastaScriptLates;
	}

}
