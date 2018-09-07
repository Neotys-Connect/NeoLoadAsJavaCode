package com.neotys.testing.framework.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by hrexed on 05/07/18.
 */
public final class NeoloadFileUtils {

	public static Path getResourcesPath() {
		return Paths.get("src", "test", "resources");
	}

	public static Path getTargetPath() {
		return Paths.get("target");
	}

	public static Path getNeoLoadTargetPath() {
		return Paths.get("target", "neoload");
	}

	public static Path getNeoLoadProjectPath(final String projectName) {

		return Paths.get("target", "neoload", cleanProjectName(projectName));
	}

	private static String cleanProjectName(final String projectName) {
		//TODO add all required replacements
		return projectName
				.replaceAll(" ", "_")
				.replaceAll("@", "_");
	}

	public static String getJsonFileName() {
		//TODO allow customization
		return "NeoLoad_List_Of_Test.json";
	}

}
