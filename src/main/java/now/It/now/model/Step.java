package now.It.now.model;


public enum Step {

  ADVANCE("A"),
  LEFT("G"),
  RIGHT("D");

  private String code;

  Step(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

}
