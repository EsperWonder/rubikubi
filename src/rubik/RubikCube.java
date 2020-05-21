package rubik;

public class RubikCube {
  
  private Face faces[][][];

  public RubikCube() {
    // 6 sides, each a 2x2 grid
    this.faces = new Face[6][2][2];
  }
  
  public Face[][] getSide(int i) {
    return this.faces[i];
  }
}
