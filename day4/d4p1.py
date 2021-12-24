# System libraries
import copy

# PyPi libraries
import numpy as np


# Local libraries
from libraries import common
from libraries import day4


if __name__ == '__main__':
    draws_fn = 'day4/draws'
    draws_file_handler = common.FileHandler(draws_fn)
    draws = draws_file_handler.read_csv()[0]

    boards_fn = 'day4/boards'
    boards = day4.Boards(np.loadtxt(boards_fn, dtype=int))
    board_count = int(boards.get_boards_count(5))
    for i in range(1, board_count + 1):
        board = day4.Board(boards.get_board_data(i))
        boards.add_board(board)

    have_winner = False
    for draw in draws:
        if not have_winner:
            for board in boards.boards:
                board.mark_match(int(draw))
                check_won = board.check_win()
                if check_won:
                    have_winner = True
                    unmarked_sum = board.get_unmarked_sum()
                    last_draw = int(draw)
                    break

    print(unmarked_sum*last_draw)
