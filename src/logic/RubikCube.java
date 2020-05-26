package logic;

public class RubikCube {

  // Possible rotations :
  // Front, Back, Left, Right, Up, Down
  // Each rotation has an inverted counterpart

  /** Number of sides the RubikCube has. */
  public static final int NB_SIDES = 6;
  /** Number of cells each side has. */
  public static final int NB_CELLS = 4;

  private Face[][] cells;

  public RubikCube() {
    this.cells = new Face[NB_SIDES][NB_CELLS];
    this.init();
  }

  public void init() {
    for (int s = 0; s < NB_SIDES; s++) {
      for (int c = 0; c < NB_CELLS; c++) {
        this.cells[s][c] = Face.getFace(s);
      }
    }
  }

  public Face[] getSide(int side) {
    return this.cells[side];
  }

  public Face getFace(int side, int face) {
    return this.getSide(side)[face];
  }
  
  ///////////////
  // ROTATIONS //
  ///////////////

  public void F() {
    Face tmp;

    // Ring rotation
    tmp = cells[3][1];
    cells[3][1] = cells[5][0];
    cells[5][0] = cells[1][2];
    cells[1][2] = cells[4][3];
    cells[4][3] = tmp;

    tmp = cells[3][3];
    cells[3][3] = cells[5][1];
    cells[5][1] = cells[1][0];
    cells[1][0] = cells[4][2];
    cells[4][2] = tmp;

    // Face rotation
    tmp = cells[0][0];
    cells[0][0] = cells[0][2];
    cells[0][2] = cells[0][3];
    cells[0][3] = cells[0][1];
    cells[0][1] = tmp;
  }

  public void Fi() {
    Face tmp;

    // Ring rotation
    tmp = cells[4][3];
    cells[4][3] = cells[1][2];
    cells[1][2] = cells[5][0];
    cells[5][0] = cells[3][1];
    cells[3][1] = tmp;

    tmp = cells[4][2];
    cells[4][2] = cells[1][0];
    cells[1][0] = cells[5][1];
    cells[5][1] = cells[3][3];
    cells[3][3] = tmp;

    // Face rotation
    tmp = cells[0][1];
    cells[0][1] = cells[0][3];
    cells[0][3] = cells[0][2];
    cells[0][2] = cells[0][0];
    cells[0][0] = tmp;
  }

  public void B() {
    Face tmp;

    // Ring rotation
    tmp = cells[5][3];
    cells[5][3] = cells[3][2];
    cells[3][2] = cells[4][0];
    cells[4][0] = cells[1][1];
    cells[1][1] = tmp;

    tmp = cells[5][2];
    cells[5][2] = cells[3][0];
    cells[3][0] = cells[4][1];
    cells[4][1] = cells[1][3];
    cells[1][3] = tmp;
    
    // Face rotation
    tmp = cells[2][2];
    cells[2][2] = cells[2][3];
    cells[2][3] = cells[2][1];
    cells[2][1] = cells[2][0];
    cells[2][0] = tmp;
  }

  public void Bi() {
    Face tmp;

    // Ring rotation
    tmp = cells[1][1];
    cells[1][1] = cells[4][0];
    cells[4][0] = cells[3][2];
    cells[3][2] = cells[5][3];
    cells[5][3] = tmp;

    tmp = cells[1][3];
    cells[1][3] = cells[4][1];
    cells[4][1] = cells[3][0];
    cells[3][0] = cells[5][2];
    cells[5][2] = tmp;

    // Face rotation
    tmp = cells[2][0];
    cells[2][0] = cells[2][1];
    cells[2][1] = cells[2][3];
    cells[2][3] = cells[2][2];
    cells[2][2] = tmp;
  }
  
  public void L() {
    Face tmp;
    
    // Ring rotation
    tmp = cells[2][1];
    cells[2][1] = cells[5][2];
    cells[5][2] = cells[0][2];
    cells[0][2] = cells[4][2];
    cells[4][2] = tmp;
    
    tmp = cells[2][3];
    cells[2][3] = cells[5][0];
    cells[5][0] = cells[0][0];
    cells[0][0] = cells[4][0];
    cells[4][0] = tmp;
    
    // Face rotation
    tmp = cells[3][2];
    cells[3][2] = cells[3][3];
    cells[3][3] = cells[3][1];
    cells[3][1] = cells[3][0];
    cells[3][0] = tmp;
  }
  
  public void Li() {
    Face tmp;
    
    // Ring rotation
    tmp = cells[4][2];
    cells[4][2] = cells[0][2];
    cells[0][2] = cells[5][2];
    cells[5][2] = cells[2][1];
    cells[2][1] = tmp;
    
    tmp = cells[4][0];
    cells[4][0] = cells[0][0];
    cells[0][0] = cells[5][0];
    cells[5][0] = cells[2][3];
    cells[2][3] = tmp;
    
    // Face rotation
    tmp = cells[3][0];
    cells[3][0] = cells[3][1];
    cells[3][1] = cells[3][3];
    cells[3][3] = cells[3][2];
    cells[3][2] = tmp;
  }
  
  public void R() {
    Face tmp;
    
    // Ring rotation
    tmp = cells[0][1];
    cells[0][1] = cells[5][1];
    cells[5][1] = cells[2][2];
    cells[2][2] = cells[4][1];
    cells[4][1] = tmp;
    
    tmp = cells[0][3];
    cells[0][3] = cells[5][3];
    cells[5][3] = cells[2][0];
    cells[2][0] = cells[4][3];
    cells[4][3] = tmp;
    
    // Face rotation
    tmp = cells[1][0];
    cells[1][0] = cells[1][2];
    cells[1][2] = cells[1][3];
    cells[1][3] = cells[1][1];
    cells[1][1] = tmp;
  }
  
  public void Ri() {
    Face tmp;
    
    // Ring rotation
    tmp = cells[4][1];
    cells[4][1] = cells[2][2];
    cells[2][2] = cells[5][1];
    cells[5][1] = cells[0][1];
    cells[0][1] = tmp;
    
    tmp = cells[4][3];
    cells[4][3] = cells[2][0];
    cells[2][0] = cells[5][3];
    cells[5][3] = cells[0][3];
    cells[0][3] = tmp;
    
    // Face rotation
    tmp = cells[1][1];
    cells[1][1] = cells[1][3];
    cells[1][3] = cells[1][2];
    cells[1][2] = cells[1][0];
    cells[1][0] = tmp;
  }
  
  public void U() {
    Face tmp;
    
    // Ring rotation
    tmp = cells[0][1];
    cells[0][1] = cells[1][1];
    cells[1][1] = cells[2][1];
    cells[2][1] = cells[3][1];
    cells[3][1] = tmp;
    
    tmp = cells[0][0];
    cells[0][0] = cells[1][0];
    cells[1][0] = cells[2][0];
    cells[2][0] = cells[3][0];
    cells[3][0] = tmp;
    
    // Face rotation
    tmp = cells[4][0];
    cells[4][0] = cells[4][2];
    cells[4][2] = cells[4][3];
    cells[4][3] = cells[4][1];
    cells[4][1] = tmp;
  }
  
  public void Ui() {
    Face tmp;
    
    // Ring rotation
    tmp = cells[3][1];
    cells[3][1] = cells[2][1];
    cells[2][1] = cells[1][1];
    cells[1][1] = cells[0][1];
    cells[0][1] = tmp;
    
    tmp = cells[3][0];
    cells[3][0] = cells[2][0];
    cells[2][0] = cells[1][0];
    cells[1][0] = cells[0][0];
    cells[0][0] = tmp;
    
    // Face rotation
    tmp = cells[4][1];
    cells[4][1] = cells[4][3];
    cells[4][3] = cells[4][2];
    cells[4][2] = cells[4][0];
    cells[4][0] = tmp;
  }
  
  public void D() {
    Face tmp;
    
    // Ring rotation
    tmp = cells[0][2];
    cells[0][2] = cells[3][2];
    cells[3][2] = cells[2][2];
    cells[2][2] = cells[1][2];
    cells[1][2] = tmp;
    
    tmp = cells[0][3];
    cells[0][3] = cells[3][3];
    cells[3][3] = cells[2][3];
    cells[2][3] = cells[1][3];
    cells[1][3] = tmp;
    
    // Face rotation
    tmp = cells[5][0];
    cells[5][0] = cells[5][2];
    cells[5][2] = cells[5][3];
    cells[5][3] = cells[5][1];
    cells[5][1] = tmp;
  }
  
  public void Di() {
    Face tmp;
    
    // Ring rotation
    tmp = cells[1][2];
    cells[1][2] = cells[2][2];
    cells[2][2] = cells[3][2];
    cells[3][2] = cells[0][2];
    cells[0][2] = tmp;
    
    tmp = cells[1][3];
    cells[1][3] = cells[2][3];
    cells[2][3] = cells[3][3];
    cells[3][3] = cells[0][3];
    cells[0][3] = tmp;
    
    // Face rotation you absolute dickshit
    tmp = cells[5][1];
    cells[5][1] = cells[5][3];
    cells[5][3] = cells[5][2];
    cells[5][2] = cells[5][0];
    cells[5][0] = tmp;
  }
}
