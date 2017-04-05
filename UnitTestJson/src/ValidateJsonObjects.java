
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import org.junit.Assert;
import org.junit.Test;


/*import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestExtensionContext;*/


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by abhoendie on 3/30/2017.
 */
public class ValidateJsonObjects{


    @Test
   /* @DisplayName("Is JSON Content Valid")*/
    public void isJsonContentValid() {

        ValidateJsonObjects obj = new ValidateJsonObjects();
        //obj.getFile("locale-en.json");
        String jsonString = obj.getFiles("locale-en.json");

        Assert.assertTrue(obj.isJSONValid(jsonString));
        // System.out.println(jsonString);
    }

    private String getFiles(String fileName) {

        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        return result.toString();

    }

    public boolean isJSONValid(String test) {
        try {
            JsonParser parser = new JsonParser();
            //String data = object.testJsonFile();
            parser.parse(test);
            return true;
        } catch (JsonSyntaxException jse) {
            System.out.println("Not a valid Json String:" + jse.getMessage());
        }

        return false;
    }

    /*@Override
    public void handleTestExecutionException(TestExtensionContext testExtensionContext, Throwable throwable) throws Throwable {
        if (throwable instanceof IOException) {
            return;
        }
        throw throwable;
    }*/
}
