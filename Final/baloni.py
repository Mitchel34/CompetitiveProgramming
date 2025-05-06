import sys
def min_arrows(heights):
    arrows = {}      # arrows[h] = number of arrows currently at height h
    shots = 0
    for h in heights:
        # if there’s an arrow at h+1, it will pop this balloon
        if arrows.get(h+1, 0) > 0:
            arrows[h+1] -= 1
            # that same arrow continues at height h
            arrows[h] = arrows.get(h, 0) + 1
        else:
            # otherwise we must shoot a new arrow at height h
            shots += 1
            arrows[h] = arrows.get(h, 0) + 1
    return shots

def main():
    data = sys.stdin.read().split()
    n = int(data[0])
    heights = list(map(int, data[1:]))
    print(min_arrows(heights))

if __name__ == "__main__":
    main()
