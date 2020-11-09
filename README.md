# Reactive Programming:
- Dựa trên sự kiện lập trình không đồng bộ.
- có thể tạo ra luồng dữ liệu này từ bất kỳ thứ gì : thay đổi biến,
sự kiện click, http call, data storage, errors và có thể là không thứ gì.
- Bất đồng bộ có nghĩa là mọi module code thì mỗi module chạy trên từng thread
riêng của nó, và do đó cùng một lúc có nhiều khối mã được thực thi.
- Hoàn thành nhanh hơn thực hiện tuần tự do mỗi module chạy trên từng thread riêng của nó,
do đó cùng lúc thực thi được nhiều khối mã.
# Reactive Extension: Rx
- Là sự kết hợp của những ý tưởng tốt nhất từ Observer pattern, Iterator pattern và functional programming.
# RxJava:
- Là Reactive Extension cho Java, follow theo Observer pattern.
# RxAndroid:
- Là Rx cho Android.
- Scheduler được thêm vào RxAndroid có vai trò quan trọng trong hỗ trợ đa luồng.
- Có 2 loại Scheduler được quan tâm nhiều: Scheduler.io() và AndroidSchedulers.mainThread()
	+ Scheduler.io: //không liên quan đến CPU dùng để xử lí db, gọi api...
		* Khi dùng cái này thì sẽ không dùng đến CPU, nó thực hiện các công việc chuyên sâu như networks call,
		đọc đĩa/file, database, … Nó duy trì được pool của thread.
	+ AndroidSchedulers.mainThread:
		* Nó cung cấp quyền truy cập đến Main Thread/UI Thread.
- SubscribeOn: quyết định request sẽ được thực hiện trên thread nào. chỉ 1 subo, nhiều thì lấy cái đầu
- ObserveOn: quyết định observer trong subsribe sẽ được thực hiện ở task nào.
## Disposable:
    - Được dùng để loại bỏ đăng kí (subsription) khi Observer không còn lắng nghe Observable nữa.
    - Vì khi Activity hay Fragment bị destroy thì Observer đăng kí vẫn sống và nó cố gắng thay đổi UI trên Activity/Fragment đã destroy
    => trường hợp này dẫn đến leak(rò rỉ) bộ nhớ.
Nội dung cần nghiên cứu thêm với các từ khóa sau:
- Single, just,...
- callable
- map, filter
# References
[https://viblo.asia/p/rxjava-rxandroid-phan-1-nhung-khai-niem-co-ban-YWOZr2rPZQ0#_schedulersio-5]()
[https://viblo.asia/p/rxjava-rxandroid-phan-2-examples-use-rx-in-android-gDVK22L2KLj#_2-buoc-co-ban-nhat-1]()

*Chúng ta thường quen với kiểu lập trình tuần tự(được gọi là lập trình đồng bộ). Giờ đây tư duy lập trình hướng sự kiện hay
bất đồng bộ đã được sử dụng trong lập trình Android.
*Về cơ bản, RxJava sẽ follow theo Observer Pattern. Khi lập trình được chia làm 2 phần:
- 1 là phần tạo sự kiện như click button sẽ tạo request rest API
- phần còn lại là lắng nghe và phản hồi 1 cách bất đồng bộ với sự kiện cụ thế nào đó như update view, xử lí kết quả trả về...
RxJava có phiên bản đặc biệt cho Android là RxAndroid. Về triết lý cơ bản thì giống nhau, RxAndroid sẽ được thêm mở rộng và hỗ trợ thêm
api cho Android.

# Lí do nên dùng RxAndroid thay cho Async Task?
Async Task có nhiệm vụ là thực hiện các tác vụ nặng dưới background, sau khi hoàn thành sẽ cập nhật lên UI Thread.
Lí do:
- error: giúp bắt lỗi
- code: ngắn gọn hơn, với async task khi xử lí multithread thì việc quản lí async task là 1 ác mộng
- không cần dùng đến Context. AsyncTask sẽ gặp lỗi memory leak nếu tạo inner class và truyền vào context không đúng cách.
