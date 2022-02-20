package d13;

enum FoldInstruction {
    FOLD1 ('x', 655),
    FOLD2 ('y', 447),
    FOLD3 ('x', 327),
    FOLD4 ('y', 223),
    FOLD5 ('x', 163),
    FOLD6 ('y', 111),
    FOLD7 ('x', 81),
    FOLD8 ('y', 55),
    FOLD9 ('x', 40),
    FOLD10 ('y', 27),
    FOLD11 ('y', 13),
    FOLD12 ('y', 6);

    final char axis;
    final int position;

    FoldInstruction(char axis, int position) {
        this.axis = axis;
        this.position = position;
    }
}
