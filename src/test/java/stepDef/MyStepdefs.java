package stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import static org.junit.Assert.assertEquals;

public class MyStepdefs {
    private String element;
    private int count;
    private String element1;
    private int count1;

    @Given("the data of RCB players")
    public JSONObject theDataOfRCBPlayers() {
        String jsonFileLocation="src/test/java/test.json";
        Path jsonFilePath = Paths.get(jsonFileLocation);
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader= Files.newBufferedReader(jsonFilePath))
        {
            String line;
            while((line= bufferedReader.readLine())!= null)
            {
                stringBuilder.append(line);
            }
            return  new JSONObject(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Then("validate if the team has only {int} foreign players")
    public void validateIfTheTeamHasOnlyForeignPlayers(int arg0) {
        JSONObject data = theDataOfRCBPlayers();
        JSONArray elements = data.getJSONArray("player");
        ArrayList<String> hm = new ArrayList<>();
        for (int i = 0; i < elements.length(); i++) {
            element = data.getJSONArray("player").getJSONObject(i).getString("country");
            hm.add(element);
        }
        Iterator<String> itr = hm.iterator();
        while (itr.hasNext()) {
            String test1=itr.next();
            if (!(Objects.equals(test1, "India")))
            {
                count++;
            }
        }
        if (count == 4) {
            Assert.assertEquals("4",count);
            System.out.println("4 foreign players are there in the Team");
        }
        if (count > 4) {
            System.out.println("More than 4 foreign players");
        } else if(count<4) {
            System.out.println("Less than 4 foreign players");
        }
    }



    @Then("validate if the team has atleast {int} wicket keeper")
    public void validateIfTheTeamHasAtleastWicketKeeper(int arg0) {
        JSONObject data = theDataOfRCBPlayers();
        JSONArray elements1 = data.getJSONArray("player");
        ArrayList<String> hm = new ArrayList<>();
        for (int i = 0; i < elements1.length(); i++) {
            element1 = data.getJSONArray("player").getJSONObject(i).getString("role");
            hm.add(element1);
        }
        Iterator<String> itr = hm.iterator();
        while (itr.hasNext()) {
            String test1=itr.next();
            if ((Objects.equals(test1, "Wicket-keeper")))
            {
                count1++;
            }
        }
        if (count1>=1) {
            Assert.assertEquals("1",count1);
            System.out.println(count1+" wicket keeper is there");
        }
        else  {
            System.out.println("No wicket Keepers are there");
        }

    }


}
