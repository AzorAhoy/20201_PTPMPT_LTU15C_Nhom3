import cv2
import numpy as np

img = cv2.imread('E:\\BerkeleyTower.PNG', cv2.IMREAD_GRAYSCALE)

#Sobel
img_sobelx1 = cv2.Sobel(img,cv2.CV_64F,1,0,ksize=1)
img_sobely1 =cv2.Sobel(img, cv2.CV_64F, 0,1,ksize=1)

img_sobelx3 = cv2.Sobel(img,cv2.CV_64F,1,0,ksize=3)
img_sobely3 =cv2.Sobel(img, cv2.CV_64F, 0,1,ksize=3)

img_sobelx5 = cv2.Sobel(img,cv2.CV_64F,1,0,ksize=5)
img_sobely5 =cv2.Sobel(img, cv2.CV_64F, 0,1,ksize=5)

img_sobelx7 = cv2.Sobel(img,cv2.CV_64F,1,0,ksize=7)
img_sobely7 =cv2.Sobel(img, cv2.CV_64F, 0,1,ksize=7)

img_sobelx9 = cv2.Sobel(img,cv2.CV_64F,1,0,ksize=9)
img_sobely9 =cv2.Sobel(img, cv2.CV_64F, 0,1,ksize=9)

cv2.imshow("Original Image",img)

cv2.imshow("Sobelx1",img_sobelx1)
#cv2.imshow("Sobely1",img_sobely1)

#cv2.imshow("Sobelx3",img_sobelx3)
#cv2.imshow("Sobely3",img_sobely3)

#cv2.imshow("Sobelx5",img_sobelx5)
#cv2.imshow("Sobely3",img_sobely5)

#cv2.imshow("Sobelx7",img_sobelx7)
#cv2.imshow("Sobely3",img_sobely7)

#cv2.imshow("Sobelx9",img_sobelx9)
#cv2.imshow("Sobely9",img_sobely9)

cv2.imwrite("E:\Python\img\img_sobelx1.png",img_sobelx1)
#cv2.imwrite("E:\Python\img\img_sobelx3.png",img_sobelx3)
#cv2.imwrite("E:\Python\img\img_sobelx5.png",img_sobelx5)
#cv2.imwrite("E:\Python\img\img_sobelx7.png",img_sobelx7)
#cv2.imwrite("E:\Python\img\img_sobelx9.png",img_sobelx9)

#cv2.imwrite("E:\Python\img\img_sobely1.png",img_sobely1)
#cv2.imwrite("E:\Python\img\img_sobely3.png",img_sobely3)
#cv2.imwrite("E:\Python\img\img_sobely5.png",img_sobely5)
#cv2.imwrite("E:\Python\img\img_sobely7.png",img_sobely7)
#cv2.imwrite("E:\Python\img\img_sobely9.png",img_sobely9)

cv2.waitKey(0)
cv2.destroyAllWindows()
