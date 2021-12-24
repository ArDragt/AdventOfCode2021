# PyPi libraries
import numpy as np


class Bingo:
    def __init__(self, data) -> None:
        self.data = data
        self.boards = []
        self.complete_boards = {}

    def get_boards_count(self, board_row_count):
        _rows, _cols = self.data.shape
        return _rows / board_row_count

    def get_board_data(self, board_number):
        _start_row = (board_number - 1)*5
        _end_row = _start_row + 5
        return self.data[_start_row:_end_row]

    def add_board(self, board):
        self.boards.append(board)

    def get_board_by_id(self, board_id):
        _board = [board for board in self.boards if board.board_id == board_id]
        if _board:
            return _board[0]
        else:
            return None


class Board:
    def __init__(self, board_id, data) -> None:
        self.board_id = board_id
        self.data = data
        self.matches = {
            'rows': { 1: 0, 2: 0, 3: 0, 4: 0, 5: 0 },
            'columns': { 1: 0, 2: 0, 3: 0, 4: 0, 5: 0 },
            'numbers': []
        }
    
    def mark_match(self, draw):
        _matches = np.nonzero(self.data == draw)
        if _matches[0].size > 0:
            self.matches['rows'][_matches[0][0] + 1] += 1
            self.matches['columns'][_matches[1][0] + 1] += 1
            self.matches['numbers'].append(draw)

    def check_win(self):
        _row_check = any(value == 5 for value in self.matches['rows'].values())
        _column_check = any(value == 5 for value in self.matches['columns'].values())
        # _row_check = any(self.matches['rows'].values() == 5)
        # _column_check = any(self.matches['columns'].values() == 5)
        return True if _row_check or _column_check else False

    def get_unmarked_sum(self):
        return self.data[np.isin(self.data, self.matches['numbers'], invert=True)].sum()
