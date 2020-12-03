import cv2
import numpy as np

img = cv2.imread('E:\\BerkeleyTower.PNG', cv2.IMREAD_GRAYSCALE)

kernelx = np.array([[1,1,1],[0,0,0],[-1,-1,-1]])
kernely = np.array([[-1,0,1],[-1,0,1],[-1,0,1]])
img_prewittx = cv2.filter2D(img, -1, kernelx)
img_prewitty = cv2.filter2D(img, -1, kernely)

cv2.imshow("Prewitt X", img_prewittx)
cv2.imshow("Prewitt Y", img_prewitty)
cv2.imshow("Prewitt", img_prewittx + img_prewitty)

#cv2.imwrite("E:\Python\img\img_prewittx.png",img_prewittx)
#cv2.imwrite("E:\Python\img\img_prewitty.png",img_prewitty)
cv2.imwrite("E:\Python\img\img_prewitt.png",img_prewittx + img_prewitty)

cv2.waitKey(0)
cv2.destroyAllWindows()

