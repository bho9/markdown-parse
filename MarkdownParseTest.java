import static org.junit.Assert.*;
import org.junit.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);

        // assertEquals(expected, actual)
    }

    @Test
    public void test1() throws IOException
    {
        Path fileName = Path.of("test-file.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        // System.out.println(links);
        assertEquals(List.of("https://something.com", "some-page.html"), links);
        // Uses List.of because this creates an ArrayList<String> and we're comparing
        // it to "links" which is an ArrayList<String> object. If links was simply just
        // a String, List.of wouldn't be needed.
    }

    @Test
    public void test2() throws IOException
    {
        Path fileName = Path.of("test-file2.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        // System.out.println(links);
        assertEquals(List.of("testlink.com", "testlink2.com"), links);
    }

    @Test
    public void test3() throws IOException
    {
        Path fileName = Path.of("test-file3.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        // System.out.println(links);
        assertEquals(List.of("link.com", "link.html"), links);
    }

    @Test
    public void test4() throws IOException
    {
        Path fileName = Path.of("test-file4.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        ArrayList<String> expected = new ArrayList<>();
        // System.out.println(links);
        assertEquals(expected, links);
    }

    @Test
    public void test5() throws IOException
    {
        Path fileName = Path.of("test-file5.md");
	    String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        // System.out.println(links);
        assertEquals(List.of("link.com"), links);
    }
}
