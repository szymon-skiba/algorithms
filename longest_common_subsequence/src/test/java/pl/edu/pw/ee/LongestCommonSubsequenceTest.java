package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class LongestCommonSubsequenceTest {

    String redairectedFile = "src/test/resources/redirectedOutput.txt";
    String pathToTemplateNoPath = "src/test/resources/templateTableNoPath.txt";
    String pathToTemplateWithPath = "src/test/resources/templateTableWithPath.txt";

    @Test
    public void simpleVisualConfirmation_Mikolaj() {
        //given
        LongestCommonSubsequence lg = new LongestCommonSubsequence("MIKOLAJ", "IKONA");

        //when
        String result = lg.findLCS();
        System.out.println(result);
        lg.display();

        //then
        assert Objects.equals(result, "IKOA");
    }

    @Test
    public void visualDisplayWithoutFindLCS() {
        //given
        LongestCommonSubsequence lg = new LongestCommonSubsequence("często_z_odkrywaniem", "rzeczy_nie_trzeba\\n_się_spieszyć");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(redairectedFile);
        } catch (FileNotFoundException e) {
            System.out.println("Blad podczas tworzenia danych testowych" + e.getMessage());
        }
        PrintStream tmpOut = new PrintStream(fos);
        System.setErr(tmpOut);
        System.setOut(tmpOut);


        //when
        lg.display();

        try {
            byte[] f1 = Files.readAllBytes(Path.of(pathToTemplateNoPath));
            byte[] f2 = Files.readAllBytes(Path.of(redairectedFile));

            assertArrayEquals(f1, f2);
            return;
        } catch (IOException e) {
            System.out.println("Blad podczas wczytwania danych testowych" + e.getMessage());
        }

        //then
        assert false;
    }


    @Test
    public void visualDisplayWithFindLCS() {
        //given
        LongestCommonSubsequence lg = new LongestCommonSubsequence("często_z_odkrywaniem", "rzeczy_nie_trzeba\n_się_spieszyć");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(redairectedFile);
        } catch (FileNotFoundException e) {
            System.out.println("Blad podczas tworzenia danych testowych" + e.getMessage());
        }
        PrintStream tmpOut = new PrintStream(fos);
        System.setErr(tmpOut);
        System.setOut(tmpOut);


        //when
        lg.findLCS();
        lg.display();

        try {
            byte[] f1 = Files.readAllBytes(Path.of(pathToTemplateWithPath));
            byte[] f2 = Files.readAllBytes(Path.of(redairectedFile));

            assertArrayEquals(f1, f2);
            return;
        } catch (IOException e) {
            System.out.println("Blad podczas wczytwania danych testowych" + e.getMessage());
        }

        //then
        assert false;
    }

    @Test(expected = OutOfMemoryError.class)
    public void tooBigStringsGiven() {
        //given
        StringBuilder firstStr = new StringBuilder();
        StringBuilder secondStr = new StringBuilder();
        for (int i = 0; i < 999999999; i++) {
            firstStr.append('A');
            secondStr.append('A');
        }

        //when
        new LongestCommonSubsequence(firstStr.toString(), secondStr.toString());


        //then
        assert false;
    }


    @Test(expected = IllegalArgumentException.class)
    public void nullGiven() {
        //given
        LongestCommonSubsequence lg = null;
        String firstStr = null;
        String secondStr = null;

        //when
        lg = new LongestCommonSubsequence(firstStr, secondStr);

        //then
        assert false;
    }


    @Test(expected = IllegalArgumentException.class)
    public void emptyStringsGiven() {
        //given
        LongestCommonSubsequence lg = null;
        String firstStr = "";
        String secondStr = "";

        //when
        lg = new LongestCommonSubsequence(firstStr, secondStr);

        //then
        assert false;
    }


    @Test
    public void specialCharactersGiven() {


        //given
        LongestCommonSubsequence lg = null;
        String firstStr = "\0\t \n\r\\";
        String secondStr = "\0\t \nAAAA\rAAAA\\";
        lg = new LongestCommonSubsequence(firstStr, secondStr);


        //when
        String expected = "0\\t\\ n\\r\\\\\\";
        String result = lg.findLCS();


        //then
        assert expected.equals(result);
    }


    @Test
    public void polishCharactersGiven() {


        //given
        LongestCommonSubsequence lg = null;
        String firstStr = "ąśćććć";
        String secondStr = "AAAAAAąśAAAćććć";
        lg = new LongestCommonSubsequence(firstStr, secondStr);


        //when
        String expected = "ąśćććć";
        String result = lg.findLCS();


        //then
        assert expected.equals(result);
    }


    @Test()
    public void multipleSubStringsPossibleGiven_OnlyOneIsLongest() {


        //given
        LongestCommonSubsequence lg = null;
        String firstStr = "ABCDEFG12345";
        String secondStr = "ABC15ABBCC12345DEFG12345";
        lg = new LongestCommonSubsequence(firstStr, secondStr);


        //when
        String expected = "ABCDEFG12345";
        String result = lg.findLCS();


        //then
        assert expected.equals(result);
    }


}
