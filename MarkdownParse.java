// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class MarkdownParse {
    public static Map<String, List<String>> getLinks(File dirOrFile) throws IOException {
        Map<String, List<String>> result = new HashMap<>();
        if(dirOrFile.isDirectory()) {
            for(File f: dirOrFile.listFiles()) {
                result.putAll(getLinks(f));
            }
            return result;
        }
        else {
            Path p = dirOrFile.toPath();
            int lastDot = p.toString().lastIndexOf(".");
            if(lastDot == -1 || !p.toString().substring(lastDot).equals(".md")) {
                return result;
            }
            ArrayList<String> links = getLinks(Files.readString(p));
            result.put(dirOrFile.getPath(), links);
            return result;
        }
    }

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        /* if (!markdown.contains("[") && !markdown.contains("]") && !markdown.contains("(") && !markdown.contains(")"))
        {
            System.out.println("This file does not contain links.");
            return toReturn;
        }
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", currentIndex);
            if (openParen == -1)
            {
                return toReturn;
            }
            if (markdown.substring(openParen + 1, openParen + 2).equals("("))
            {
                ++openParen;
            }
            int closeParen = markdown.indexOf(")", openParen);
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;

            // System.out.println("Open parenthesis index: " + openParen);
        } */
        WordCountVisitor2 visitor = new WordCountVisitor2();
        toReturn.add(visitor.links.toString());

        return toReturn;
    }
    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        Map<String, List<String>> links = getLinks(fileName.toFile());
        System.out.println(links);
    }
}

class WordCountVisitor2 extends AbstractVisitor {
    ArrayList<String> links = new ArrayList<String>();

    @Override
    public void visit(Link link)
    {
        links.add(link.toString());
    }
}