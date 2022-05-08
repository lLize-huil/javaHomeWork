[![O38oUf.png](https://s1.ax1x.com/2022/05/08/O38oUf.png)](https://imgtu.com/i/O38oUf)

`-Xmx`：指定最大堆内存，作用于图中`1`

`-Xms`：指定堆内存空间的初始大小，作用于图`1`

`-Xmn`：等价于 `-XX:NewSize`，除了`G1`以外，其他的会作用于`2`，设置年轻代的大小，建议设置整个堆内存的`1/2`到`1/4`

`-XX:MaxMetaspaceSize=size`：作用于`3`，Java8 默认不限制 Meta 空间，一般不允许 设置该选项。

`-XX:MaxDirectMemorySize=size`：作用于`5`，堆外内存,系统可以使用的最大堆外内存，这个参 数跟 -Dsun.nio.MaxDirectMemorySize 效果相同。

`-Xss`：设置每个线程栈的字节数，影响栈的深度，作用于`4`