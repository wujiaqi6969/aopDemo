# 项目简单描述
改项目的一个设计逻辑
使用aop方式来记录上游的一个日志,根据不同的实体类转换生成相同的实体类
有一个难点:就是我们写入流水虽然是一个标准化的模型,但是我们的一个入参是不标准的,那么我们应该如何在同一个切面里面,去根据不同的入参,取到我想要的参数
满足开闭原则