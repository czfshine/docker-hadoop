import random

'''
    输出多个随机的单词文件
'''
lineprefile = 1024  # 文件行数
wordpreline = 10  # 每行有多少个单词

# 词典文件,linux下自带的
word_file = "/usr/share/dict/words"
try:
    WORDS = open(word_file).read().splitlines()
except IOError:
    WORDS = ["HADOOP", "HELLO", "HI"]


def generatefile(outpath):
    '''
    生成一个文件
    :param outpath: 输出路径
    :return: void
    '''
    out = open(outpath, "w")
    for i in range(lineprefile):
        word = [random.choice(WORDS) for j in range(wordpreline)]
        out.write("".join(map(lambda x: x + " ", word)))
        out.write("\n")

