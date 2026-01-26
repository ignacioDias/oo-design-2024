package texteditor;

import java.util.ArrayList;
import java.util.List;

public class Buffer {

    List<String> buffer = new ArrayList<>();;

    public void setBuffer(List<String> buffer) {
        this.buffer = buffer;
    }
    public List<String> getBuffer() {
        return buffer;
    }
    public String getLine(int index) {
        return buffer.get(index);
    }
    public void removeLine(int index) {
        buffer.remove(index);
    }
    public void insertLine(int index, String content) {
        buffer.add(index, content);
    }
    public int getLineCount() {
        return buffer.size();
    }
    public boolean isEmpty() {
        return buffer.isEmpty();
    }
    public void clear() {
        buffer.clear();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String line : buffer) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}