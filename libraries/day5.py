class Vents:
    def __init__(self) -> None:
        self.lines = []
        self.point_counter = {}

    def add_line(self, line):
        self.lines.append(line)

    def increment_point_counter(self, point):
        try:
            self.point_counter[point]
        except KeyError:
            self.point_counter[point] = 1
        else:
            self.point_counter[point] += 1

    def count_overlaps(self):
        return len({k:v for (k,v) in self.point_counter.items() if v >= 2})


class Line:
    def __init__(self, line_input) -> None:
        _line_parts = line_input.split(' -> ')
        self.start = {'x': int(_line_parts[0].split(',')[0]), 'y': int(_line_parts[0].split(',')[1])}
        self.end = {'x': int(_line_parts[1].split(',')[0]), 'y': int(_line_parts[1].split(',')[1])}

    def check_xy_match(self):
        if (self.start['x'] == self.end['x']) or (self.start['y'] == self.end['y']):
            return True
        else:
            return False

    def check_45_degrees(self):
        if abs(self.end['x'] - self.start['x']) == abs(self.end['y'] - self.start['y']):
            return True
        else:
            return False

    def traverse_line(self) -> list:
        _points = []
        # Vertical line
        if self.start['x'] == self.end['x']:
            if self.start['y'] < self.end['y']:
                _start_y = self.start['y']
                _end_y = self.end['y']
            else:
                _start_y = self.end['y']
                _end_y = self.start['y']
            for _y in range(_start_y, _end_y + 1):
                _points.append(f"x{self.start['x']}y{_y}")
            
        # Horizontal line
        elif self.start['y'] == self.end['y']:
            if self.start['x'] < self.end['x']:
                _start_x = self.start['x']
                _end_x = self.end['x']
            else:
                _start_x = self.end['x']
                _end_x = self.start['x']
            for _x in range(_start_x, _end_x + 1):
                _points.append(f"x{_x}y{self.start['y']}")

        # 45 degree line
        else:
            if self.start['x'] < self.end['x'] and self.start['y'] < self.end['y']:
                _x_increment = 1
                _y_increment = 1
            elif self.start['x'] < self.end['x'] and self.start['y'] > self.end['y']:
                _x_increment = 1
                _y_increment = -1
            elif self.start['x'] > self.end['x'] and self.start['y'] < self.end['y']:
                _x_increment = -1
                _y_increment = 1
            elif self.start['x'] > self.end['x'] and self.start['y'] > self.end['y']:
                _x_increment = -1
                _y_increment = -1
            _x = self.start['x']
            _y = self.start['y']
            while _x != self.end['x'] + _x_increment and _y != self.end['y'] + _y_increment:
                _points.append(f"x{_x}y{_y}")
                _x += _x_increment
                _y += _y_increment

        return _points