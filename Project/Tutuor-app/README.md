# Tutor App

Tutor App เป็นแอปพลิเคชัน Flutter สำหรับจัดการข้อมูลติวเตอร์และตารางสอน เชื่อมต่อกับ Firebase เพื่อเก็บข้อมูลใน Cloud Firestore และรองรับการใช้งานหลัก 2 ฝั่ง คือ `Tutor` และ `Admin`

## ภาพรวมโปรเจกต์

ระบบนี้ช่วยให้ติวเตอร์สมัครสมาชิก เข้าสู่ระบบ แก้ไขข้อมูลส่วนตัว เลือกวิชาที่สอน และจัดตารางเวลาสอนรายสัปดาห์ได้ ส่วนผู้ดูแลระบบสามารถดูรายชื่อติวเตอร์ทั้งหมด ค้นหา/กรองตามวิชาและช่วงเวลา เปิดดูรายละเอียด และลบข้อมูลติวเตอร์ออกจากระบบได้

## ฟีเจอร์หลัก

### Tutor

- สมัครสมาชิกติวเตอร์ใหม่
- อัปโหลดรูปโปรไฟล์
- เข้าสู่ระบบด้วยอีเมลและรหัสผ่าน
- บันทึก session การเข้าสู่ระบบด้วย `shared_preferences`
- แก้ไขข้อมูลส่วนตัว เช่น ชื่อจริง ชื่อเล่น เบอร์โทร Line อาชีพ/กิจกรรมปัจจุบัน เวลาเดินทาง และเงื่อนไขการสอน
- เลือกวิชาที่สามารถสอนได้หลายรายการ
- จัดตารางสอนรายสัปดาห์
- กำหนดช่วงเวลาเป็น `teaching` หรือ `busy`

### Admin

- เข้าสู่ระบบผู้ดูแลระบบ
- ดูรายชื่อติวเตอร์ทั้งหมด
- กรองติวเตอร์ตามวิชา
- กรองติวเตอร์ตามช่วงเวลา
- เปิดดูข้อมูลติวเตอร์รายบุคคล
- ลบข้อมูลติวเตอร์ออกจากระบบ

## เทคโนโลยีที่ใช้

- Flutter
- Dart
- Firebase Core
- Cloud Firestore
- Firebase Auth
- Firebase Storage
- Image Picker
- Shared Preferences

## โครงสร้างโปรเจกต์

```text
lib/
|- main.dart
|- firebase_options.dart
|- models/
|- screens/
|  |- admin/
|  |- home/
|  `- tutor/
|- services/
|- utils/
`- widgets/
```

ไฟล์สำคัญ:

- `lib/main.dart` เริ่มต้นแอปและกำหนด route หลัก
- `lib/screens/home/home_screen.dart` หน้าแรกสำหรับเลือกเข้าใช้งานฝั่ง Admin หรือ Tutor
- `lib/screens/tutor/` หน้าสมัครสมาชิก เข้าสู่ระบบ และ dashboard ของติวเตอร์
- `lib/screens/admin/` หน้าเข้าสู่ระบบและ dashboard ของผู้ดูแลระบบ
- `lib/services/storage_service.dart` จัดการอ่าน/เขียนข้อมูลติวเตอร์ใน Firestore
- `lib/services/filter_service.dart` จัดการการกรองข้อมูลติวเตอร์
- `lib/models/tutor_model.dart` โครงสร้างข้อมูลติวเตอร์
- `lib/widgets/schedule_grid.dart` ตารางเวลาสอนรายสัปดาห์
- `lib/utils/app_routes.dart` รวมชื่อ route ของระบบ
- `lib/utils/app_constants.dart` รวมค่าคงที่ของแอป เช่น เวลา ตารางสอน สี และรหัสผ่าน admin

## การเก็บข้อมูล

ข้อมูลติวเตอร์ถูกเก็บใน Cloud Firestore collection ชื่อ `tutors`

ตัวอย่าง field ที่ใช้งาน:

- `tutorId`
- `realName`
- `nickname`
- `email`
- `password`
- `phoneNumber`
- `lineId`
- `currentActivity`
- `travelTime`
- `teachingCondition`
- `preferredSubjects`
- `schedule`
- `profileImageBase64`
- `createdAt`
- `updatedAt`

หมายเหตุ: รูปโปรไฟล์ใน implementation ปัจจุบันถูกเก็บเป็น Base64 ใน Firestore แม้โปรเจกต์จะติดตั้ง `firebase_storage` ไว้แล้ว

## เส้นทางหลักของแอป

- `/` หน้าแรก
- `/admin-login` หน้าเข้าสู่ระบบผู้ดูแลระบบ
- `/admin-dashboard` หน้า dashboard ผู้ดูแลระบบ
- `/tutor-register` หน้าสมัครสมาชิกติวเตอร์
- `/tutor-login` หน้าเข้าสู่ระบบติวเตอร์
- `/tutor-dashboard` หน้า dashboard ติวเตอร์

## การติดตั้งและเริ่มใช้งาน

### 1. เตรียมเครื่องมือ

ติดตั้งเครื่องมือเหล่านี้ก่อนใช้งาน:

- Flutter SDK
- Dart SDK
- Android Studio หรือ VS Code
- Android Emulator หรืออุปกรณ์ Android จริง
- Firebase project

ตรวจสอบว่า Flutter พร้อมใช้งาน:

```bash
flutter doctor
```

### 2. เข้าโฟลเดอร์โปรเจกต์

```bash
cd Project/Tutuor-app
```

### 3. ติดตั้ง dependencies

```bash
flutter pub get
```

### 4. ตั้งค่า Firebase

โปรเจกต์มีไฟล์ config สำหรับ Firebase อยู่แล้ว เช่น:

- `lib/firebase_options.dart`
- `android/app/google-services.json`
- `firebase.json`

หากต้องการเชื่อมกับ Firebase project ของตัวเอง ให้สร้าง config ใหม่ด้วย FlutterFire CLI:

```bash
dart pub global activate flutterfire_cli
flutterfire configure
```

หลังจากนั้นตรวจสอบว่าไฟล์ config ของแต่ละ platform ตรงกับ Firebase project ที่ต้องการใช้งาน

### 5. รันแอป

รันบน emulator หรืออุปกรณ์ที่เชื่อมต่ออยู่:

```bash
flutter run
```

รันบน Chrome:

```bash
flutter run -d chrome
```

## การทดสอบ

รัน test ด้วยคำสั่ง:

```bash
flutter test
```

## การ build

Build เป็น Android APK:

```bash
flutter build apk
```

Build สำหรับ Web:

```bash
flutter build web
```

## บัญชีและการยืนยันตัวตน

### Tutor

- ติวเตอร์เข้าสู่ระบบด้วยข้อมูลที่เก็บใน Firestore collection `tutors`
- หลังเข้าสู่ระบบสำเร็จ ระบบจะบันทึก `tutorId` ลงใน local session

### Admin

- ผู้ดูแลระบบใช้รหัสผ่านแบบ hardcoded ภายในแอป
- ค่าอยู่ใน `lib/utils/app_constants.dart`

## ตารางสอน

ระบบตารางสอนทำงานแบบรายสัปดาห์ โดยแบ่งช่วงเวลาทีละ 30 นาที

- เวลาเริ่มต้น `08:00`
- เวลาสิ้นสุด `21:00`
- ช่วงที่แก้ไขได้ถึง `20:00`

ประเภท block ในตาราง:

- `teaching` เวลาที่มีสอน
- `busy` เวลาที่ไม่ว่าง

ผู้ดูแลระบบสามารถใช้ข้อมูลตารางนี้เพื่อกรองหาติวเตอร์ที่ว่างตามช่วงเวลาที่ต้องการได้

## ข้อจำกัดของเวอร์ชันปัจจุบัน

- รหัสผ่านติวเตอร์ยังถูกเก็บใน Firestore โดยตรง
- รหัสผ่าน admin ยังอยู่ใน source code
- รูปโปรไฟล์ถูกเก็บเป็น Base64 ใน Firestore ซึ่งอาจทำให้ document มีขนาดใหญ่
- ยังไม่มี test coverage เชิงลึกสำหรับ business logic และ UI flow
- ข้อความภาษาไทยบางส่วนใน source code เดิมยังมีปัญหา encoding

## แนวทางพัฒนาต่อ

- ย้ายการยืนยันตัวตนไปใช้ Firebase Authentication ให้ครบถ้วน
- ย้ายรูปโปรไฟล์ไปเก็บบน Firebase Storage
- เพิ่ม validation และ error handling ให้ครอบคลุมขึ้น
- เพิ่ม unit test และ widget test
- แยก admin access ออกจากการใช้รหัสผ่าน hardcoded
- ปรับ encoding ของข้อความภาษาไทยใน source code ให้ถูกต้องทั้งโปรเจกต์

## คำสั่งที่ใช้บ่อย

```bash
flutter pub get
flutter run
flutter test
flutter build apk
flutter build web
```
