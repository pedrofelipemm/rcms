package br.ufscar.rcms.servico.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class RunScriptLattes implements Runnable {

	private final String hash;

    private final String pastaScriptLates;

	public RunScriptLattes(final String hash, final String pastaScriptLates) {
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

			final ProcessBuilder pb = new ProcessBuilder(commands);
			pb.directory(new File(pastaScriptLates));

			pb.inheritIO();

			final Process proc = pb.start();

			proc.getOutputStream().close();

			final InputStream inputstream = proc.getInputStream();
	        final InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
	        final BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

	        proc.waitFor();

	        String line;
	        final StringBuilder output = new StringBuilder();
	        while ((line = bufferedreader.readLine()) != null) {
	            output.append(line);
	        }
	        System.out.println(output.toString());
	        inputstream.close();

			proc.destroy();

		} catch (final Exception e) {
			e.printStackTrace();
		}

        // TODO PEDRO CLEAN TEMP FILES

	}

	public String getPastaScriptLates() {
		return pastaScriptLates;
	}

}
