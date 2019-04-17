# coding=utf-8
import random
import os
import shutil
import sys

'''
    输出多个随机的单词文件
'''

filecount = 100  # 生成文件数量

if len(sys.argv) != 2:
    output_dir = "./input/index/"
else:
    output_dir = sys.argv[1] + "/"

print("[倒排索引]:输出测试文件到" + output_dir)
try:
    shutil.rmtree(output_dir)
except:
    pass
os.makedirs(output_dir)

for i in range(filecount):
    filename = output_dir + "word_" + str(i + 1) + ".txt"
    generatefile(filename)
