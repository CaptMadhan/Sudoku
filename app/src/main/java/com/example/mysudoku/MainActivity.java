package com.example.mysudoku;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final String[][] string=new String[9][9];
    final int[][] arraylist=new int[9][9];

    Button solve_button;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText[][] edittext= new EditText[][]{
                {
                    //1
                        findViewById(R.id.box00),
                        findViewById(R.id.box01),
                        findViewById(R.id.box02),
                        findViewById(R.id.box03),
                        findViewById(R.id.box04),
                        findViewById(R.id.box05),
                        findViewById(R.id.box06),
                        findViewById(R.id.box07),
                        findViewById(R.id.box08)
                },
                {
                    //2
                        findViewById(R.id.box10),
                        findViewById(R.id.box11),
                        findViewById(R.id.box12),
                        findViewById(R.id.box13),
                        findViewById(R.id.box14),
                        findViewById(R.id.box15),
                        findViewById(R.id.box16),
                        findViewById(R.id.box17),
                        findViewById(R.id.box18)
                },
                {
                    //3
                        findViewById(R.id.box20),
                        findViewById(R.id.box21),
                        findViewById(R.id.box22),
                        findViewById(R.id.box23),
                        findViewById(R.id.box24),
                        findViewById(R.id.box25),
                        findViewById(R.id.box26),
                        findViewById(R.id.box27),
                        findViewById(R.id.box28)
                },
                {
                    //4
                        findViewById(R.id.box30),
                        findViewById(R.id.box31),
                        findViewById(R.id.box32),
                        findViewById(R.id.box33),
                        findViewById(R.id.box34),
                        findViewById(R.id.box35),
                        findViewById(R.id.box36),
                        findViewById(R.id.box37),
                        findViewById(R.id.box38)
                },
                {
                    //5
                        findViewById(R.id.box40),
                        findViewById(R.id.box41),
                        findViewById(R.id.box42),
                        findViewById(R.id.box43),
                        findViewById(R.id.box44),
                        findViewById(R.id.box45),
                        findViewById(R.id.box46),
                        findViewById(R.id.box47),
                        findViewById(R.id.box48)
                },
                {
                    //6
                        findViewById(R.id.box50),
                        findViewById(R.id.box51),
                        findViewById(R.id.box52),
                        findViewById(R.id.box53),
                        findViewById(R.id.box54),
                        findViewById(R.id.box55),
                        findViewById(R.id.box56),
                        findViewById(R.id.box57),
                        findViewById(R.id.box58)
                },
                {
                    //7
                        findViewById(R.id.box60),
                        findViewById(R.id.box61),
                        findViewById(R.id.box62),
                        findViewById(R.id.box63),
                        findViewById(R.id.box64),
                        findViewById(R.id.box65),
                        findViewById(R.id.box66),
                        findViewById(R.id.box67),
                        findViewById(R.id.box68)
                },
                {
                    //8
                        findViewById(R.id.box70),
                        findViewById(R.id.box71),
                        findViewById(R.id.box72),
                        findViewById(R.id.box73),
                        findViewById(R.id.box74),
                        findViewById(R.id.box75),
                        findViewById(R.id.box76),
                        findViewById(R.id.box77),
                        findViewById(R.id.box78)
                },
                {
                    //9
                        findViewById(R.id.box80),
                        findViewById(R.id.box81),
                        findViewById(R.id.box82),
                        findViewById(R.id.box83),
                        findViewById(R.id.box84),
                        findViewById(R.id.box85),
                        findViewById(R.id.box86),
                        findViewById(R.id.box87),
                        findViewById(R.id.box88)
                }
        };
        int i,j;
        for(i=0;i<9;i++){
            for(j=0;j<9;j++){
                string[i][j]="0";
            }
        }


        solve_button=findViewById(R.id.solve_button);
        solve_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i,j;
                for(i=0;i<9;i++){
                    for(j=0;j<9;j++){
                        if(!edittext[i][j].getText().toString().isEmpty())
                            string[i][j]=edittext[i][j].getText().toString();
                    }
                }

                for(i=0;i<9;i++){
                    for(j=0;j<9;j++){
                            arraylist[i][j]=Integer.parseInt(string[i][j]);
                    }
                }

//                for(i=0;i<9;i++) {
//                    for (j = 0; j < 9; j++) {
//                        edittext[i][j].setText(string[i][j]);
//                    }
//                }
                //
                Sudoku sudoku = new Sudoku(arraylist);
                if (sudoku.solve()) {
                    solve_button.setText("solved");
                    sudoku.display();
                }
                else{
                    solve_button.setText("not solvable");
                }


                //
            }
            //
            class Sudoku {
                private int[][] board;
                public static final int EMPTY = 0; // empty cell
                public static final int SIZE = 9; // size of our Sudoku grids

                public Sudoku(int[][] board) {
                    this.board = new int[SIZE][SIZE];

                    for (int i = 0; i < SIZE; i++) {
                        for (int j = 0; j < SIZE; j++) {
                            this.board[i][j] = board[i][j];
                        }
                    }
                }

                // we check if a possible number is already in a row
                private boolean isInRow(int row, int number) {
                    for (int i = 0; i < SIZE; i++)
                        if (board[row][i] == number)
                            return true;

                    return false;
                }

                // we check if a possible number is already in a column
                private boolean isInCol(int col, int number) {
                    for (int i = 0; i < SIZE; i++)
                        if (board[i][col] == number)
                            return true;

                    return false;
                }

                // we check if a possible number is in its 3x3 box
                private boolean isInBox(int row, int col, int number) {
                    int r = row - row % 3;
                    int c = col - col % 3;

                    for (int i = r; i < r + 3; i++)
                        for (int j = c; j < c + 3; j++)
                            if (board[i][j] == number)
                                return true;

                    return false;
                }

                // combined method to check if a number possible to a row,col position is ok
                private boolean isOk(int row, int col, int number) {
                    return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row, col, number);
                }

                // Solve method. We will use a recursive BackTracking algorithm.
                public boolean solve() {
                    for (int row = 0; row < SIZE; row++) {
                        for (int col = 0; col < SIZE; col++) {
                            // we search an empty cell
                            if (board[row][col] == EMPTY) {
                                // we try possible numbers
                                for (int number = 1; number <= SIZE; number++) {
                                    if (isOk(row, col, number)) {
                                        // number ok. it respects sudoku constraints
                                        board[row][col] = number;
                                        if (solve()) { // we start backtracking recursively
                                            return true;
                                        } else { // if not a solution, we empty the cell and we continue
                                            board[row][col] = EMPTY;
                                        }
                                    }
                                }

                                return false; // we return false
                            }
                        }
                    }

                    return true; // sudoku solved
                }
                public void display() {
                    for (int i = 0; i < SIZE; i++) {
                        for (int j = 0; j < SIZE; j++) {
                            edittext[i][j].setText("" +board[i][j]);
                        }
                    }
                }



            }







            //
        });


        
    }

}
