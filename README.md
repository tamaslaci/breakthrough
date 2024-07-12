## Breakthrough project
> Study project for the 'Programming technologies' class at the University for practicing programming concepts in JAVA language relating to GUIs,
> using the swing library's elements like: `JFrame`, `JPanel`, `JMenu`,`JOptionPane`, `JButton`, ... etc., and Layout solutions.

This code is the implementation of a simple table game, like chess, but here, the players only have one kind of figure to move with.
The players goal is to reach the opposite side of the table with one figure.
For this the pawn like pieces can be moved upward (for the white player or downward for the black) in straight or diagonal directions, one field per round.
Figures can't step backward and they can knock down each other.
The GUI can be controlled with mouse:
in each round, on the actual side (black or white), a pawn figure should be selected and after selection, the fields, available for the next step, are highlighted.
Green fields for normal - forward steps, and red fields for knocking an opponent. After clicking the choosen next field for the actual figure,
the opponent can do the same until one reaches the other side of the table.
The table size can also be modified in the menu on the game's menubar (small 6x6, medium 8x8, large 10x10).

Sample gameplay images:

![start](https://github.com/user-attachments/assets/9afe07d2-8d9f-456f-9b4d-722531de1116)
![white_select](https://github.com/user-attachments/assets/629b14cb-5564-4a9e-8f80-d34c474aed36)
![first_step](https://github.com/user-attachments/assets/7e0bf283-556b-4852-8442-48604f0845ea)
![attacked_field](https://github.com/user-attachments/assets/f9c513ca-58db-4900-b435-9115f10390af)
![only_correct_steps](https://github.com/user-attachments/assets/42612fce-c872-4d7e-ac41-d7af26b4f2c2)
![reach_end_step](https://github.com/user-attachments/assets/0949c08e-28d7-44b7-94b7-66da64bf4297)
![win_with_step](https://github.com/user-attachments/assets/78d4c7d1-3d7e-487b-ae0a-5fe7466c44bc)
