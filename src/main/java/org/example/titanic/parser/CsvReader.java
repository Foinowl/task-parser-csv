package org.example.titanic.parser;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.example.titanic.exception.CSVException;
import org.example.titanic.exception.DefinitionsArgumentException;
import org.example.titanic.exception.HeaderMissingException;
import org.example.titanic.utils.Constants;

public class CsvReader implements Closeable, Iterable<CsvReader.CsvDetails> {

    private final CsvDetails csvDetails;

    private InputStreamReader inputStream;

    private BufferedReader bufferedReader;

    private Boolean heading;

    private String separator;

    private String regex;


    private CsvReader(final Boolean heading, final String separator) {
        setHeading(heading);
        setSeparator(separator);
        setRegex();
        this.csvDetails = new CsvDetails();
    }

    private CsvReader(final InputStream inputStream, final Boolean heading,
                      final String separator) {
        this(heading, separator);
        initInputStream(inputStream);
    }

    private CsvReader(final File file, final Boolean heading,
                      final String separator) {
        this(heading, separator);
        initFile(file);
    }

    private void setRegex() {
        this.regex =
            "(?:" + this.separator + "|\\n|^)(\"(?:(?:\"\")*[^\"]*)*\"|[^\"" + this.separator + "\\n]*|(?:\\n|$))";
    }

    public void parse() {
        readHeader();

        try {
            while (bufferedReader.ready()) {
                String[] line = readLineAndSplit();
                csvDetails.addLine(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readHeader() {
        if (!heading) {
            return;
        }

        String[] lineOfHead = readLineAndSplit();
        csvDetails.setLineToHead(lineOfHead);
    }

    private String[] readLineAndSplit() {
        try {
            return getWordsByRegex(bufferedReader.readLine());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] getWordsByRegex(String line) {
        Matcher matcher = Pattern.compile(this.regex).matcher(line);
        int i = 0;
        List<String> arr = new ArrayList<>();
        while (matcher.find()) {
            arr.add(matcher.group(1).replaceAll("^\"|\"$", ""));
        }
        return arr.toArray(new String[0]);
    }

    private void mapByHead() {

    }

    private void mapByHeadIndex() {

    }

    private void initInputStream(InputStream inputStream) {
        setInputStream(new InputStreamReader(inputStream));
        setBufferedReader(new BufferedReader(this.inputStream));
    }

    private void initFile(File file) {
        try {
            setInputStream(new FileReader(file));
            setBufferedReader(new BufferedReader(this.inputStream));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setInputStream(final InputStreamReader inputStream) {
        this.inputStream = inputStream;
    }

    private void setBufferedReader(final BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public void setHeading(final Boolean heading) {
        this.heading = heading;
    }

    public void setSeparator(final String separator) {
        this.separator = separator;
    }

    @Override
    public void close() throws IOException {
        if (this.inputStream != null) {
            this.inputStream.close();
        }

        if (this.bufferedReader != null) {
            this.bufferedReader.close();
        }
    }

    @Override
    public Iterator<CsvDetails> iterator() {
        return new CsvIterator(this);
    }

    public class CsvDetails {
        private final Map<String, Integer> headings = new HashMap<>();

        private final List<String[]> lines = new LinkedList<>();

        private String[] currentLine;

        private List<String[]> getLines() {
            return lines;
        }

        private void setLineToHead(String[] line) {
            for (int i = 0; i < line.length; i++) {
                headings.put(line[i], i);
            }
        }

        private void addLine(String[] line) {
            this.lines.add(line);
        }

        private void setCurrentLine(final String[] currentLine) {
            this.currentLine = currentLine;
        }

        public String getByIndex(int index) {
            return this.currentLine[index];
        }

        public String getByColumn(String column) {
            if (heading) {
                return getByIndex(headings.get(column));
            }

            throw new HeaderMissingException("Can't take values by headings");
        }
    }

    private static class CsvIterator implements Iterator<CsvDetails> {

        private final ListIterator<String[]> listIterator;

        private final CsvDetails csvDetails;

        public CsvIterator(final CsvReader reader) {
            this.csvDetails = reader.csvDetails;
            this.listIterator = reader.csvDetails.getLines().listIterator();
        }

        @Override
        public boolean hasNext() {
            return this.listIterator.hasNext();
        }

        @Override
        public CsvDetails next() {

            if (hasNext()) {
                csvDetails.setCurrentLine(this.listIterator.next());
                return csvDetails;
            }
            return null;
        }
    }

    public static class Builder {

        private InputStream inputStream;

        private File file;

        private Boolean heading;

        private String separator = Constants.COMMA;

        public Builder setInputStream(final InputStream inputStream) {
            this.inputStream = inputStream;
            return this;
        }

        public Builder setFile(final File file) {
            this.file = file;
            return this;
        }

        public Builder setHeading(final Boolean heading) {
            this.heading = heading;
            return this;
        }

        public Builder setSeparator(final String separator) {
            this.separator = separator;
            return this;
        }

        public CsvReader build() {
            if (inputStream != null && file != null) {
                throw new DefinitionsArgumentException("Decide to use inputStream or file");
            }

            CsvReader csvReader = null;

            if (file != null) {
                if (!file.exists()) {
                    throw new CSVException("file has no founded");
                }
                csvReader = new CsvReader(file, heading, separator);
            }

            if (inputStream != null) {
                csvReader = new CsvReader(inputStream, heading, separator);
            }

            return csvReader;
        }
    }
}
