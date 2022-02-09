package d10;

class CharacterStatus {
    boolean matched;
    char value;

    CharacterStatus(char value, boolean matched) {
        this.matched = matched;
        this.value = value;
    }

    void setMatched() {this.matched = true;}
}
