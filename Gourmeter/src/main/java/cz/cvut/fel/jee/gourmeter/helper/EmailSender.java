package cz.cvut.fel.jee.gourmeter.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.cvut.fel.jee.gourmeter.ejb.FacilitySessionBean;

@Stateless
public class EmailSender {
	private static final Logger log = LoggerFactory
			.getLogger(FacilitySessionBean.class);

	/**
	 * Odesle zpravu, dle zvolene sablony a se zadanymi parametry, na zadanou
	 * emailovou adresu
	 * 
	 * @param emailAddress
	 *            - emailova adresa
	 * @param params
	 *            - parametry, ktere se maji aplikovat na sablonu. Klic je nazev
	 *            parametru bez < a >. Hodnota se bere jako Object a aplikuje se
	 *            na ni metoda toString()
	 * @param templateFile
	 * @see TemplateType
	 */
	public void sendMessage(String emailAddress, Map<String, Object> params,
			TemplateType templateFile) {
		String address = null;

		// vyber sablony
		if (templateFile != null)
			address = templateFile.getTemplateFileName();

		// kontrola dostupnosti souboru
		File file = new File(address);

		String template = null;
		if ((file.exists()) && (file.isFile()) && (file.canRead())) {
			template = readTemplateFromFile(file);
		} else {
			log.warn("Can't access the file!" + address);
		}

		if (template == null) {
			log.warn("Read method returned empty template String! The email could not be sent!");
			return;
		}

		// substituce parametru
		for (Map.Entry<String, Object> entrySet : params.entrySet()) {
			template = template.replaceAll("<" + entrySet.getKey() + ">",
					entrySet.getValue().toString());
		}

		// odeslani emailu
		// TODO

	}

	private String readTemplateFromFile(File file) {
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			log.warn("Template file not found! " + e.getMessage().toString());
			// e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);

		// nacteni souboru a vraceni stringu
		StringBuilder result = new StringBuilder();

		String pom = null;
		try {
			pom = br.readLine();
			while (pom != null) {
				result.append(pom);
				pom = br.readLine();
			}
		} catch (IOException e) {
			log.warn("Error while reading file!");
			// e.printStackTrace();
		}

		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				log.warn("Unable to close BufferedReader for file!");
				// e.printStackTrace();
			}
		}
		if (fr != null) {
			try {
				fr.close();
			} catch (IOException e) {
				log.warn("Unable to close FileReader for file!");
				// e.printStackTrace();
			}
		}
		return result.toString();
	}
}
