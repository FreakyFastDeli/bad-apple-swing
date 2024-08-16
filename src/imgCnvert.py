import cv2
import numpy as np
import os
from PIL import Image

def generate_combined_frames(input_folder, output_file):
    with open(output_file, 'w') as outfile:
        counter = 0
        for filename in sorted(os.listdir(input_folder)):
            counter += 1
            if filename.endswith((".png", ".jpg", ".jpeg", ".bmp")):
                image_path = os.path.join(input_folder, filename)

                img = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)

                _, img = cv2.threshold(img, 127, 255, cv2.THRESH_BINARY)

                kernel = np.ones((3, 3), np.uint8)
                img = cv2.morphologyEx(img, cv2.MORPH_OPEN, kernel)

                height, width = img.shape
                for y in range(height):
                    row = []
                    in_black = False
                    start = 0

                    for x in range(width):
                        pixel = img[y, x]

                        if pixel == 0:  # black
                            if not in_black:
                                start = x
                                in_black = True
                        else:  # white
                            if in_black:
                                row.append(start)
                                row.append(x - 1)
                                in_black = False


                    if in_black:
                        row.append(start)
                        row.append(width - 1)


                    if row:
                        outfile.write(" ".join(map(str, row)))
                    if y < height - 1:
                        outfile.write(",")

                outfile.write("\n")
                if counter % 100 == 0:
                    print(f"Processed {counter} frames")
        print(f"Processed {counter} frames")


input_folder = "./in"
output_file = "./out/frames.txt"

generate_combined_frames(input_folder, output_file)
