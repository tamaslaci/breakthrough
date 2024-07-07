/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package iz85zg.breakthrough;

/**
 *
 * @author iz85zg
 */
public class Breakthrough {
    /*
        Feladatmegoldás - elemzés
        Megjelenés: "JButton táblázat"
        6 féle JButton objektum:
            a.: üres, b.: bábu ikon (fehér), c.: bábu ikon (fekete), d.: léphető üres mező (választott bábura kattintás után),
            e.: támadható mező (fehér) ellenség bábuval (választott fekete bábura kattintás után)
            f.: támadható mező (fekete) ellenség bábuval (választott fehér bábura kattintás után)
        3 féle JFrame/JPanel játéktér:
            1.: 6*6, 2.: 8*8, 3.: 10*10 tábla

        Fehér és Fekete bábuk 2-2 sorban a táblázat alján és tetején.
        Bábura kattintással léphető, vagy üthető, mezők kijelölése.
        Saját bábut nem lehet ütni (fekete bábu fehéret üthet és fehér bábu feketét).
        Minden lépés után játékállás ellenőrzés és győztes keresés.

        Objektum hierarchia:
        JButton  ->  Field <<abstract>>  -> Empty field  -> Step field
                                         -> Pawn field <<abstract>>
                                             -> White Pawn field -> Attacked white Pawn field
                                             -> Black Pawn field -> Attacked black Pawn field
                     Board > Small Board (6*6)
                           > Medium Board (8*8)
                           > Large Board (10*10)
        JFrame  ->  JMenuBar  +  JPanel (Board) + JLabel
    */
    public static void main(String[] args) {
        BreakThroughGUI breakthrough = new BreakThroughGUI();
    }
}
