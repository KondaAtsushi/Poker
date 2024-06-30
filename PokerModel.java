package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerModel {

    /**ゲーム回数*/
    int games;

    /**チップ数*/
    int chips;

    /** 数字毎に手札の枚数を数える*/
    int count[];

    int two, three, four, st, straight, flash, roy, royal,add;
    int spade, heart, dia, club;

    /**山札*/
    List<Integer> deckcards;

    /**手札*/
    List<Integer> handcards;

    /**送信ボタンに表示する文字列*/
    String buttonLabel;
    /**to player message*/
    String message;

    public PokerModel() {
        deckcards = new ArrayList<>();
        handcards = new ArrayList<>();
    }

    /**ゲーム開始*/
    public void reset() {
        games = 0;
        chips = 500;
    }

    /**次のゲームのためにカードを配りなおす*/
    public void nextgame() {
        deckcards.clear();
        for (int i = 0; i <= 51; i++) {
            deckcards.add(i);
        }

        Collections.shuffle(deckcards);

        handcards.clear();
        for (int i = 0; i < 5; i++) {
            int n = deckcards.remove(0);
            handcards.add(n);
        }

        System.out.printf("%d:", deckcards.size());
        for (int n : deckcards) {
            System.out.printf("%d ", n);
        }
        System.out.println();

        message = "交換するカードをチェックしてください";
        buttonLabel = "交換";
        games++;
    }
    
    /**indexで指定されたカードを山札から補充したカードに置き換える*/
    public void change(List<String> index) {
        System.out.println("index=" + index);
        for (int i = 0; i < index.size(); i++) {
            int c = deckcards.remove(0);/**山札の先頭除去*/
            handcards.set(Integer.parseInt(index.get(i)), c);/**指定場所入れ替え*/
        }
        evaluate();
        buttonLabel = "次のゲーム";
    }

    /**チップの増減*/
    public void evaluate() {
        countNumber();
        int red = countRed();
        int seven = countSeven();
        int flash = countsort();
        int point = 0;

        if (royal == 1 && flash == 1) {
            message = "★★ロイヤルストレートフラッシュ★★";
            point = 5000;
        } else if (straight == 1 && flash == 1) {
            message = "☆ストレート・フラッシュ☆";
            point = 1000;
        } else if (four == 1) {
            message = "フォーカード";
            point = 400;
        } else if (three == 1 && two == 1) {
            message = "フルハウス";
            point = 320;
        } else if (flash == 1) {
            message = "💡フラッシュ💡";
            point = 300;
        } else if (straight == 1) {
            message = "ストレート";
            point = 200;
        } else if (three == 1) {
            message = "スリーカード";
            point = 150;
        } else if (two == 2) {
            message = "ツーペア";
            point = 100;
        } else if (two == 1) {
            message = "ワンペア";
            point = 50;
        } else if (red == 5) {
            message = "レッド";
            point = 50;
        } else if (seven > 0) {
            message = "セブン";
            point = seven * 10;
        } else {
            message = "ハイカード";
            point = -200;
        }
        chips += point*add;
        message += ":" + point;

        if (chips < 0) {
            message = "ゲームオーバー";
        }
    }

    /**JSPから呼び出されるメソッド*/
    public int getGames() {
        return games;
    }

    /**現在のチップ数返す*/
    public int getChips() {
        return chips;
    }

    /**５枚中i番目のカード番号返す*/
    public int getHandcardAt(int i) {
        return handcards.get(i);
    }

    /**送信ボタンのラベル返す*/
    public String getButtonLabel() {
        return buttonLabel;
    }

    /**プレイヤーメッセージ返す*/
    public String getMessage() {
        return message;
    }

    public void setHandcards(int a, int b, int c, int d, int e) {
        handcards.set(0, a);
        handcards.set(1, b);
        handcards.set(2, c);
        handcards.set(3, d);
        handcards.set(4, e);
    }
    
    /**セブン*/
    int countSeven() {
        int sve = 0;
        for (int i = 0; i < 5; i++) {
            if (handcards.get(i) % 13 == 6) {
                sve++;
            }
        }
        return sve;
    }
    
    /**レッド*/
    int countRed() {
        int redcount = 0;
        for (int i = 0; i < 5; i++) {
            if (handcards.get(i) >= 13 && handcards.get(i) <= 38) {
                redcount++;
            }
        }
        return redcount;
    }
    
    /**フラッシュ*/
    int countsort() {
        spade = heart = dia = club = 0;
        flash = 0;
        for (int i = 0; i < 5; i++) {
            if (handcards.get(i) <= 12) {
                spade++;
            } else if (handcards.get(i) <= 26) {
                heart++;
            } else if (handcards.get(i) <= 39) {
                dia++;
            } else {
                club++;
            }
        }
        if (spade == 5 || heart == 5 || dia == 5 || club == 5) {
            flash = 1;
        }
        return flash;
    }
    
    /**手札の数字の枚数を数える*/
    void countNumber() {
        count = new int[13];
        two = three = four = 0;
        st = straight = 0;
        roy = royal = 0;
        for (int c : handcards) {
            int x = c % 13;

            for (int i = 0; i < 13; i++) {
                if (i == x) {
                    count[i]++;
                }
            }
        }

        for (int i = 0; i < 13; i++) {
            /**同位札の加算*/
            if (count[i] == 2) {
                two++;
            } else if (count[i] == 3) {
                three++;
            } else if (count[i] == 4) {
                four++;
            }

            /**ストレート*/
            if (count[i] == 1) {
                st++;
                if (st == 5) {
                    straight = 1;
                }
            } else if (count[i] == 0) {
                st = 0;
            }
        }

        /**ロイヤルストレートフラッシュ*/
        for (int j = 9; j < 13; j++) {
            if (count[j] == 1) {
                roy++;
                if (roy == 4 && count[0] == 1) {
                    royal = 1;
                }
            } else if (count[j] == 0) {
                roy = 0;
            }
        }

        for (int n : count) {
            System.out.printf("%d ", n);
        }
        System.out.println();
    }
    
}
