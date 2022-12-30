package animals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        UI game = new UI(getFileName(args), getObjectMapper(args));
        game.start();
    }

    private static ObjectMapper getObjectMapper(String[] args) {
        ObjectMapper objectMapper = new JsonMapper();
        if (args.length >= 2 && args[0].equals("-type")) {
            switch (args[1]) {
                case "xml" -> objectMapper = new XmlMapper();
                case "yaml" -> objectMapper = new YAMLMapper();
                default -> objectMapper = new JsonMapper();
            }
        }
        return objectMapper;
    }

    private static String getFileName(String[] args) {
        String fileExt = "json";
        if (args.length >= 2 && args[0].equals("-type")) {
            fileExt = args[1];
        }
        if (!Locale.getDefault().getLanguage().equals("en")){
            return String.format("animals_%s.%s", Locale.getDefault().getLanguage(), fileExt);
        } else {
            return String.format("animals.%s", fileExt);
        }
    }
}