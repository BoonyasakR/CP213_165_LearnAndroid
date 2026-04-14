# Tutor App

แอปจัดการข้อมูลติวเตอร์ที่พัฒนาด้วย Flutter และ Firebase รองรับการใช้งานทั้งฝั่งติวเตอร์และแอดมิน โดยติวเตอร์สามารถสมัครสมาชิก เข้าสู่ระบบ แก้ไขโปรไฟล์ เลือกวิชาที่สอน และจัดตารางสอนได้ ส่วนแอดมินสามารถดูรายชื่อติวเตอร์ทั้งหมด ค้นหาตามวิชาและช่วงเวลา เปิดดูข้อมูลติวเตอร์ และลบข้อมูลออกจากระบบได้

## ฟีเจอร์หลัก

- สมัครสมาชิกติวเตอร์พร้อมอัปโหลดรูปโปรไฟล์
- เข้าสู่ระบบติวเตอร์ด้วยอีเมลและรหัสผ่าน
- จดจำ session ของติวเตอร์ด้วย `shared_preferences`
- แก้ไขข้อมูลโปรไฟล์ เช่น ชื่อเล่น เบอร์โทร Line อาชีพปัจจุบัน เวลาเดินทาง และเงื่อนไขการสอน
- เลือกวิชาที่สอนได้หลายรายการ
- จัดตารางสอนรายสัปดาห์ แยกช่วง `teaching` และ `busy`
- ฝั่งแอดมินสามารถกรองติวเตอร์ตามวิชาและช่วงเวลาได้
- แอดมินสามารถเปิดดูโปรไฟล์ติวเตอร์จากรายการ และลบข้อมูลติวเตอร์ได้
- ใช้ Firestore เป็นแหล่งเก็บข้อมูลหลัก

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

- `lib/main.dart` กำหนดการเริ่มต้นแอปและ route หลัก
- `lib/screens/home/home_screen.dart` หน้าแรกสำหรับเลือกเข้าใช้งานฝั่ง Admin หรือ Tutor
- `lib/screens/tutor/` หน้าสมัครสมาชิก เข้าสู่ระบบ และ dashboard ของติวเตอร์
- `lib/screens/admin/` หน้าเข้าสู่ระบบและ dashboard ของแอดมิน
- `lib/services/storage_service.dart` จัดการอ่าน/เขียนข้อมูลติวเตอร์ใน Firestore
- `lib/services/filter_service.dart` กรองข้อมูลติวเตอร์ตามวิชาและเวลา

## การเก็บข้อมูล

ข้อมูลติวเตอร์ถูกเก็บใน collection ชื่อ `tutors` บน Cloud Firestore โดยมีข้อมูลสำคัญ เช่น:

- `nickname`
- `realName`
- `email`
- `password`
- `phoneNumber`
- `lineId`
- `preferredSubjects`
- `schedule`
- `profileImageBase64`
- `createdAt`
- `updatedAt`

หมายเหตุ:

- รูปโปรไฟล์ใน implementation ปัจจุบันถูกเก็บเป็น Base64 ใน Firestore
- `firebase_storage` ถูกติดตั้งไว้แล้ว แต่ใน flow ปัจจุบันยังไม่ได้ใช้เป็นที่เก็บรูปหลัก

## การเริ่มต้นใช้งาน

### 1. เตรียมเครื่องมือ

ติดตั้งให้พร้อมก่อน:

- Flutter SDK
- Dart SDK (มากับ Flutter)
- Android Studio หรือ VS Code
- Emulator หรืออุปกรณ์จริง
- Firebase project

ตรวจสอบว่า Flutter พร้อมใช้งาน:

```bash
flutter doctor
```

### 2. ติดตั้ง dependencies

```bash
flutter pub get
```

### 3. ตั้งค่า Firebase

โปรเจกต์นี้ใช้ Firebase และมีไฟล์ตั้งค่าบางส่วนอยู่แล้ว เช่น:

- `lib/firebase_options.dart`
- `android/app/google-services.json`
- `firebase.json`
- `.firebaserc`

ถ้าต้องการเชื่อมกับ Firebase project ของตัวเอง แนะนำให้สร้าง config ใหม่ด้วย FlutterFire CLI:

```bash
dart pub global activate flutterfire_cli
flutterfire configure
```

หลังจากนั้นตรวจสอบว่าไฟล์ config ของแต่ละ platform ถูกต้องตาม project ที่ต้องการใช้งาน

### 4. รันแอป

```bash
flutter run
```

ถ้าต้องการรันบน web:

```bash
flutter run -d chrome
```

## เส้นทางการใช้งานในแอป

Route หลักในระบบ:

- `/` หน้าแรก
- `/admin-login` หน้าเข้าสู่ระบบแอดมิน
- `/admin-dashboard` หน้า dashboard แอดมิน
- `/tutor-register` หน้าสมัครสมาชิกติวเตอร์
- `/tutor-login` หน้าเข้าสู่ระบบติวเตอร์
- `/tutor-dashboard` หน้า dashboard ติวเตอร์

## บัญชีและการยืนยันตัวตน

### Tutor

- ติวเตอร์เข้าสู่ระบบด้วยข้อมูลที่เก็บใน Firestore collection `tutors`
- หลังเข้าสู่ระบบสำเร็จ ระบบจะบันทึก `tutorId` ลงใน local session

### Admin

- แอดมินใช้รหัสผ่านแบบ hardcoded ในแอป
- ค่ารหัสผ่านถูกกำหนดไว้ใน `lib/utils/app_constants.dart`

หมายเหตุด้านความปลอดภัย:

- implementation ปัจจุบันเก็บรหัสผ่านติวเตอร์แบบ plain text
- admin password อยู่ใน source code โดยตรง
- หากจะนำไปใช้งานจริง ควรย้ายไปใช้ระบบ auth ที่ปลอดภัยกว่า เช่น Firebase Authentication และหลีกเลี่ยงการเก็บรหัสผ่านตรง ๆ

## ตารางสอน

ระบบตารางสอนของติวเตอร์ทำงานแบบรายสัปดาห์ โดยใช้ช่วงเวลา 30 นาทีต่อช่อง

- เวลาเริ่มต้น `08:00`
- เวลาสิ้นสุด `21:00`
- ช่วงที่เปิดให้แก้ไขใน grid ถึง `20:00`

ประเภท block ในตาราง:

- `teaching` สำหรับเวลาที่มีสอน
- `busy` สำหรับเวลาที่ไม่ว่าง

ฝั่งแอดมินสามารถใช้ข้อมูลในตารางนี้เพื่อกรองหาติวเตอร์ที่ว่างตามช่วงเวลาได้

## การทดสอบ

รัน test ด้วยคำสั่ง:

```bash
flutter test
```

ปัจจุบันในโปรเจกต์มี test พื้นฐานอยู่ที่ `test/widget_test.dart`

## การ build

ตัวอย่างคำสั่ง build:

```bash
flutter build apk
```

```bash
flutter build web
```

## ข้อจำกัดของเวอร์ชันปัจจุบัน

- มีข้อความภาษาไทยบางส่วนใน source ที่เคยถูกบันทึกด้วย encoding ไม่ถูกต้อง
- การจัดการสิทธิ์และรหัสผ่านยังไม่เหมาะกับ production
- รูปโปรไฟล์ยังเก็บเป็น Base64 ใน Firestore ซึ่งอาจทำให้ document มีขนาดใหญ่
- ยังไม่มี test coverage เชิงลึกสำหรับ business logic และ UI flow

## ข้อเสนอแนะสำหรับการพัฒนาต่อ

- ย้ายการยืนยันตัวตนไปใช้ Firebase Authentication
- ย้ายรูปโปรไฟล์ไปเก็บบน Firebase Storage
- แยก model และ validation ให้ชัดขึ้น
- เพิ่ม unit test และ widget test
- ปรับ encoding ของข้อความภาษาไทยใน source ให้ถูกต้องทั้งโปรเจกต์
- แยก admin access ออกจากการใช้รหัสผ่าน hardcoded

## คำสั่งที่ใช้บ่อย

```bash
flutter pub get
flutter run
flutter test
flutter build apk
flutter build web
```
