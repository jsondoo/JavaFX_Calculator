public enum Operand {
  ADD,SUB,MUL,DIV;

    @Override
    public String toString() {
        switch(name()){
            case "ADD":
                return "+";
            case "SUB":
                return "-";
            case "MUL":
                return "*";
            default:
                return "/";
        }
    }
}
