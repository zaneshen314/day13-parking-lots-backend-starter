



## 后端：

**Prompt1：生成获取策略接口**

1、生成一个名为 `ParkingService` 的 Java 类，位于 `org.afs.pakinglot.Service` 包中。这个类应该有一个 `getParkingStrategy` 方法，该方法返回所有可用停车策略的列表。该类应包含一个 `ParkingBoy` 对象和一个 `Map<String, ParkingStrategy>` 对象。

2、生成一个名为 `ParkingController` 的 Java 类，位于 `org.afs.pakinglot.controller` 包中。这个类应该有一个 `getParkingStrategy` 方法，该方法通过调用 `ParkingService` 的 `getParkingStrategy` 方法来返回所有可用停车策略的列表。该类应使用 `@RestController` 注解，并映射到 `/parking` 路径。

3、生成相关的Test，使用given，when，then的格式



**Prompt2：修改ParkingLot和Ticket变为jpa实体**

1、将 ParkingLot修改为一个 JPA 实体，包含 `id`、`name` 和 `capacity` 字段，并提供相应的 getter 和 setter 方法。

2、将Ticket修改为一个 JPA 实体，包含 `id`、`plateNumber`、`position` 字段，并与 `ParkingLot` 类有多对一的关系。提供相应的 getter 和 setter 方法。

3、生成一个名为 `ParkingLotRepository` 的 Java 接口。这个接口应该继承 JpaRepository

4、生成一个名为 `TicketRepository` 的 Java 接口，。这个接口应该继承 JpaRepository。



**Prompt3:改造策略类并加入容器中**

1、将ParkingStrategy的所有实现类改造，结合TicketRepository，并注入到Spring容器当中去

2、创建一个名为 `ParkingStrategyConfig` 的 Spring 配置类。这个类应定义一个用 `@Bean` 注解的方法，该方法返回一个 `Map<String, ParkingStrategy>` 类型的对象。方法需要接收三个 `ParkingStrategy` 类型的 Bean 参数：`SequentiallyStrategy`、`MaxAvailableStrategy` 和 `AvailableRateStrategy`。这些策略应被添加到 `Map` 中，并使用特定的字符串常量作为键。



**Prompt4：生成停车Api**

1、新增一个API，请求方式为POST,路径/park/{strategy}，入参是Car和策略，调用ParkingService的park方法

2、实现ParkingService的park方法，结合Jpa，逻辑包括，

​	2.1 检查车牌号是否存在
​	2.2 根据策略查找停车场
​	2.3 判断停车场是否停满
​	2.4 创建并返回停车票
初始数据有三个停车场，分别为The Plaza Park 9个位子，City Mall Garage12个位子，Office Tower Parking 9个位子
3、生成相关的Test，使用given，when，then的格式



**Prompt5：生成取车Api**

1、新增一个API，请求方式为POST,路径/fetch，入参是TicketBo ,其中记录了取车的车牌号，调用ParkingService的fetch方法

2、实现ParkingService的fetch方法，结合Jpa，逻辑包括，

​	根据车牌号查找车辆是否存在，如果存在则返回车辆，如果不存在则返回空
3、生成相关的Test，使用given，when，then的格式



**Prompt6：生成获取所有停车场状态Api**

1、新增一个API，请求方式为Get，入参是TicketBo ,其中记录了取车的车牌号，调用ParkingService提供的getParkingLots方法

2、实现ParkingService的getParkingLots方法，结合Jpa，逻辑包括，

1. **获取所有停车场**：调用 `parkingLotRepository.findAll()` 获取所有停车场的列表 `parkingLots`。
2. **遍历停车场并生成 `ParkingLotVo`**：使用 Java 8 流式操作 (`stream()`) 遍历所有停车场，并为每个停车场生成一个对应的 `ParkingLotVo` 对象

3、生成相关的Test，使用given，when，then的格式



**Prompt7：Ticket支持计费**

如果基于我现在的代码 我想在park和fetch记录时间，停车时记录停车时间，结束时间计算停车时长，一小时5元，未达到1小时按1小时算





## 前端：

**prompt8：生成组件架构**

使用React开发一个前端的停车场页面，暂时先把页面划分成三个组件，一个大组件 ParkingLot 里面包含着两个小组件，一个组件是 ParkingLotOperator，一个组件是 ParkingLotSituation，先不要具体实现页面，把组件的结构构建好，并在 App. Js 中渲染 ParkingLot 大组件，所有组件使用 const xxx = （）的格式定义把这个prompt优化一下



**prompt9：数组模拟停车场**

现在我们需要在 ParkingLotSituation 中模拟一些数据。假设我们有三个停车场，分别名为 The Plaza Park（容量为 9 个车位）、City Mall Garage（容量为 12 个车位）和 Office Tower Parking（容量为 9 个车位）。为每个停车场绘制一个表格来呈现停车情况，例如容量为 9 的停车场应绘制一个 3x3 的表格，容量为 12 的停车场应绘制一个 3x4 的表格。请注意，表格不需要显示外侧边框，但内侧边框需要显示，并且三个停车场应位于同一行，在每个停车场下方展示停车场的名称。
使用三个数组来模拟每个停车场的情况。例如：
The Plaza Park 停了两辆车，则在第一个和第二个格子中绘制 "x"，其他格子保持空白；
City Mall Garage 停了三辆车，则在第一行的前三个格子中绘制 "x"；
Office Tower Parking 的所有格子都为空白。
接下来，将停车场中有车的情况中的 "x" 替换为一个组件，该组件形状为带有圆角的长方形，底色为 #b0f2b8，并在其中展示车牌号。可以调整模拟数据中数组的属性以实现这一要求。



**prompt10：生成输入框及按钮**

现在我们需要实现 ParkingLotOperator 组件，该组件包含以下元素：
输入框：标签为 "Plate Number"。使用 setState 维护一个变量 plateNumber，当输入框内容变化时更新 plateNumber。
下拉框：选项包括 "Standard", "Smart", "SuperSmart"。使用 setState 维护一个变量 parkingStrategy，当下拉框内容变化时更新 parkingStrategy。
按钮：内容为 "park"。当按下按钮时，打印日志，输出 plateNumber 和 parkingStrategy。
按钮：内容为 "fetch"。当按下按钮时，打印日志。
这四个元素应位于同一行，并且每个元素之间有一定的间距。元素内部的内容和元素本身也应有一定的间距（使用 padding）。两个按钮的底色为 #a7d9fe。
请根据上述要求实现 ParkingLotOperator 组件。



**prompt11** ：获取车辆



创建一个 js 文件专门存放请求，baseUrl 是http://localhost:8080，并在 ParkingLot 组件渲染完毕时发一个 get 请求 (使用 Axios 和 then，catch，finally 的方式)，路径是http://localhost:8080，它返回的是所有停车场的数据，示例如下： [ { "id": 1, "name": "The Plaza Park", "tickets": [ { "plateNumber": "ABC 123", "position": 1, "parkingLot": 1 }, { "plateNumber": "ABC 124", "position": 2, "parkingLot": 1 }, { "plateNumber": "ABC 125", "position": 3, "parkingLot": 1 } ] }, { "id": 2, "name": "City Mall Garage", "tickets": [] }, { "id": 3, "name": "Office Tower Parking", "tickets": [] } ] 其中 name 是停车场的名字，tickets 是这个停车场的情况，plateNumber 是车牌号，用于渲染 Car 组件，position 是这个停车场的位置，也就是我们当前在 ParkingLotSituation 渲染的表格的位置，根据这个接口返回的数据来重构当前在 ParkingLotProvider 的数组，并在 ParkingLotSituation 中渲染数据, 使用 useContext 管理数组，并使用 useReducer 触发数据的变更



**prompt12**：停车

在 ParkingLotOperator 中，当用户输入 Plate Number 与 ParkingStrategy 后，点击 Park，会发送一个 post 请求，把这两个参数放在请求的 body 中传过去后端，路径是 baseUrl + /park，后端返回的数据结构如下： { "plateNumber": "ABC 123", "position" : 4, "parkingLot": 3 } plateNumber 是本次 park 的车牌号，parkingLot 是停车场的 id（我们这里定义 The Plaza Park 的 id 是 1，City Mall Garage 的 id 是 2，Office Tower Parking 的 id 是 3 且 id 都不会改变），这个返回例子说明 ABC 123 在 3 号停车场也就是 Office Tower Parking 的 4 号位（第二排第一个）停车了，需要在该停车场对应的位置渲染一个 Car 组件（不要再发一次 get 请求）



**prompt13：**取车

 ParkingLotOperator 中，当用户输入 Plate Number, 点击 Fetch，会发送一个 post 请求，Plate Number 放在请求的 body 中传过去后端，路径是 baseUrl + /fetch，后端返回的数据结构如下： { "plateNumber": "ABC 123" } plateNumber 是本次 park 的车牌号，我们需要将这个车牌的车从我们的停车场中移除