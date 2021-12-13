class Locator:
    def __init__(self) -> None:
        self.tracker = {
            "forward": 0,
            "down": 0,
            "up": 0,
            "aim": 0
        }

    def parse_move(self, instruction: str):
        return instruction.split(' ')

    def move(self, direction, distance):
        self.tracker[direction] += distance

    def aim(self, direction, distance):
        if direction == 'down':
            self.tracker['aim'] += distance
        elif direction == 'up':
            self.tracker['aim'] -= distance
        elif direction == 'forward':
            self.tracker['forward'] += distance
            self.tracker['down'] += self.tracker['aim']*distance

    def get_distance(self):
        return self.tracker['forward']

    def get_depth(self):
        return self.tracker['down'] - self.tracker['up']
