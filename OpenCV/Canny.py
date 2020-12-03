import cv2
import numpy as np

img = cv2.imread('E:\\BerkeleyTower.PNG', cv2.IMREAD_GRAYSCALE)

img_canny = cv2.Canny(img,100,200)
img_canny2= cv2.Canny(img,100,100)

cv2.imshow("Canny", img_canny)
cv2.imshow("Canny2",img_canny2)

cv2.imwrite("E:\Python\img\img_canny.png",img_canny)
cv2.imwrite("E:\Python\img\img_canny.png2",img_canny2)

cv2.waitKey(0)
cv2.destroyAllWindows()
