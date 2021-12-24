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
    bingo = day4.Bingo(np.loadtxt(boards_fn, dtype=int))
    board_count = int(bingo.get_boards_count(5))
    for i in range(1, board_count + 1):
        board = day4.Board(i, bingo.get_board_data(i))
        bingo.add_board(board)

    complete_counter = 0
    for draw in draws:
        for board in bingo.boards:
            try:
                bingo.complete_boards[board.board_id]
            except KeyError:
                board_complete = False
            else:
                board_complete = True

            if not board_complete:
                board.mark_match(int(draw))
                check_complete = board.check_win()
                if check_complete:
                    complete_counter += 1
                    bingo.complete_boards[board.board_id] = int(draw)

    # Get last complete board
    last_winning_board = list(bingo.complete_boards.keys())[-1]
    last_draw = bingo.complete_boards[last_winning_board]
    board = bingo.get_board_by_id(last_winning_board)
    unmarked_sum = board.get_unmarked_sum()

    print(unmarked_sum*last_draw)
