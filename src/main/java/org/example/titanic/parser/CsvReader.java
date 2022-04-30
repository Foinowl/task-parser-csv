package org.example.titanic.parser;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class CsvReader implements Closeable, Iterable<CsvReader.CsvDetails> {

    private InputStreamReader inputStream;

    private BufferedReader bufferedReader;

    private Boolean heading;

    private String separator;

    private CsvDetails csvDetails = new CsvDetails();


    public void parse() throws IOException {
        readHeader();

        while (bufferedReader.ready()) {
            String[] line = readLineAndSplit();
            csvDetails.addLine(line);
        }
    }

    private void readHeader() throws IOException {
        if (!heading) {
            return;
        }

        String[] lineOfHead = readLineAndSplit();
        csvDetails.setLineToHead(lineOfHead);
    }

    private String[] readLineAndSplit() throws IOException {
        return getWordsBySeparator(bufferedReader.readLine());
    }

    private String[] getWordsBySeparator(String line) {
        return line.trim().split(this.separator);
    }

    private void mapByHead() {

    }

    private void mapByHeadIndex() {

    }

    public void initInputStream(InputStream inputStream) {
        setInputStream(new InputStreamReader(inputStream));
        setBufferedReader(new BufferedReader(this.inputStream));
    }

    private CsvReader setInputStream(final InputStreamReader inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    private CsvReader setBufferedReader(final BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
        return this;
    }

    public CsvReader setHeading(final Boolean heading) {
        this.heading = heading;
        return this;
    }

    public CsvReader setSeparator(final String separator) {
        this.separator = separator;
        return this;
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

    public static class CsvDetails {
        private Map<String, Integer> headings;

        private List<String[]> lines = new LinkedList<>();

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
            return currentLine[headings.get(column)];
        }
    }

    private static class CsvIterator implements Iterator<CsvDetails> {

        private ListIterator<String[]> listIterator;

        private CsvDetails csvDetails;

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
}
