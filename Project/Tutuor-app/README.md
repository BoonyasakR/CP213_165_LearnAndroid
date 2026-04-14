# Tutor App

<<<<<<< ours
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
=======
## ภาพรวมโปรเจกต์

Tutor App เป็นแอปพลิเคชันสำหรับจัดการข้อมูลติวเตอร์และตารางสอน พัฒนาด้วย Flutter และเชื่อมต่อกับ Firebase โดยระบบรองรับการใช้งาน 2 ฝั่งหลัก คือ `Tutor` และ `Admin`

- ฝั่ง `Tutor` สามารถสมัครสมาชิก เข้าสู่ระบบ แก้ไขข้อมูลโปรไฟล์ เลือกวิชาที่สอน และจัดการตารางสอนประจำสัปดาห์ได้
- ฝั่ง `Admin` สามารถเข้าสู่ระบบ ดูรายชื่อติวเตอร์ทั้งหมด ค้นหาตามวิชาและช่วงเวลา เปิดดูรายละเอียดติวเตอร์ และลบข้อมูลติวเตอร์ออกจากระบบได้

โปรเจกต์นี้ถูกออกแบบมาเพื่อช่วยให้การจัดเก็บข้อมูลติวเตอร์ การดูความพร้อมของตารางสอน และการจัดการข้อมูลภายในระบบทำได้ง่ายขึ้น

## เทคโนโลยีที่ใช้

- `Flutter`
- `Dart`
- `Firebase Core`
- `Cloud Firestore`
- `Firebase Storage`
- `Firebase Auth`
- `Image Picker`
- `Shared Preferences`

## Features

### ฟีเจอร์ของติวเตอร์

- สมัครสมาชิกติวเตอร์ใหม่
- อัปโหลดรูปโปรไฟล์
- เข้าสู่ระบบด้วยอีเมลและรหัสผ่าน
- บันทึก session การเข้าสู่ระบบในเครื่อง
- แก้ไขข้อมูลส่วนตัวของติวเตอร์
- เลือกวิชาที่สามารถสอนได้
- จัดการตารางสอนรายสัปดาห์
- กำหนดช่วงเวลาเป็น `teaching` หรือ `busy`

### ฟีเจอร์ของแอดมิน

- เข้าสู่ระบบแอดมิน
- ดูรายชื่อติวเตอร์ทั้งหมดในระบบ
- กรองติวเตอร์ตามวิชา
- กรองติวเตอร์ตามช่วงเวลา
- เปิดดูข้อมูลติวเตอร์รายบุคคล
- ลบข้อมูลติวเตอร์ออกจากระบบ

## Main Screens

หน้าจอหลักที่มีอยู่ในระบบประกอบด้วย:

- `Home Screen` หน้าแรกของแอปสำหรับเลือกเส้นทางการใช้งาน
- `Admin Login Screen` หน้าสำหรับเข้าสู่ระบบแอดมิน
- `Admin Dashboard` หน้าสำหรับดูรายชื่อติวเตอร์ ค้นหา และจัดการข้อมูล
- `Tutor Register Screen` หน้าสมัครสมาชิกสำหรับติวเตอร์
- `Tutor Login Screen` หน้าสำหรับเข้าสู่ระบบติวเตอร์
- `Tutor Dashboard` หน้าจัดการข้อมูลส่วนตัว วิชาที่สอน และตารางสอน
>>>>>>> theirs

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

<<<<<<< ours
- `lib/main.dart` กำหนดการเริ่มต้นแอปและ route หลัก
- `lib/screens/home/home_screen.dart` หน้าแรกสำหรับเลือกเข้าใช้งานฝั่ง Admin หรือ Tutor
- `lib/screens/tutor/` หน้าสมัครสมาชิก เข้าสู่ระบบ และ dashboard ของติวเตอร์
- `lib/screens/admin/` หน้าเข้าสู่ระบบและ dashboard ของแอดมิน
- `lib/services/storage_service.dart` จัดการอ่าน/เขียนข้อมูลติวเตอร์ใน Firestore
- `lib/services/filter_service.dart` กรองข้อมูลติวเตอร์ตามวิชาและเวลา

## การเก็บข้อมูล

ข้อมูลติวเตอร์ถูกเก็บใน collection ชื่อ `tutors` บน Cloud Firestore โดยมีข้อมูลสำคัญ เช่น:
=======
- `lib/main.dart` ใช้สำหรับเริ่มต้นแอปและกำหนด route หลัก
- `lib/screens/home/` เก็บหน้าหลักของแอป
- `lib/screens/tutor/` เก็บหน้าที่เกี่ยวข้องกับติวเตอร์
- `lib/screens/admin/` เก็บหน้าที่เกี่ยวข้องกับแอดมิน
- `lib/services/` เก็บ business logic และการเชื่อมต่อข้อมูล
- `lib/utils/` เก็บ constants, validators, routes และ theme

## การเก็บข้อมูล

ข้อมูลติวเตอร์ถูกเก็บใน collection `tutors` บน Cloud Firestore

ตัวอย่าง field ที่ใช้งาน:
>>>>>>> theirs

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

<<<<<<< ours
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
=======
## การติดตั้งและเริ่มต้นใช้งาน

### 1. ติดตั้ง dependencies
>>>>>>> theirs

```bash
flutter pub get
```

<<<<<<< ours
### 3. ตั้งค่า Firebase

โปรเจกต์นี้ใช้ Firebase และมีไฟล์ตั้งค่าบางส่วนอยู่แล้ว เช่น:
=======
### 2. ตั้งค่า Firebase

ในโปรเจกต์นี้มีไฟล์ที่เกี่ยวข้องกับ Firebase อยู่แล้ว เช่น:
>>>>>>> theirs

- `lib/firebase_options.dart`
- `android/app/google-services.json`
- `firebase.json`
- `.firebaserc`

<<<<<<< ours
ถ้าต้องการเชื่อมกับ Firebase project ของตัวเอง แนะนำให้สร้าง config ใหม่ด้วย FlutterFire CLI:

```bash
dart pub global activate flutterfire_cli
flutterfire configure
```

หลังจากนั้นตรวจสอบว่าไฟล์ config ของแต่ละ platform ถูกต้องตาม project ที่ต้องการใช้งาน

### 4. รันแอป
=======
หากต้องการเชื่อมกับ Firebase project ของตัวเอง สามารถสร้าง config ใหม่ได้ด้วยคำสั่ง:

```bash
flutterfire configure
```

### 3. รันโปรเจกต์
>>>>>>> theirs

```bash
flutter run
```

<<<<<<< ours
ถ้าต้องการรันบน web:
=======
หากต้องการรันบน Chrome:
>>>>>>> theirs

```bash
flutter run -d chrome
```

<<<<<<< ours
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
=======
## เส้นทางหลักของระบบ

- `/`
- `/admin-login`
- `/admin-dashboard`
- `/tutor-register`
- `/tutor-login`
- `/tutor-dashboard`

## การทดสอบ

ใช้คำสั่ง:
>>>>>>> theirs

```bash
flutter test
```

<<<<<<< ours
ปัจจุบันในโปรเจกต์มี test พื้นฐานอยู่ที่ `test/widget_test.dart`

## การ build

ตัวอย่างคำสั่ง build:

=======
## การ build

>>>>>>> theirs
```bash
flutter build apk
```

```bash
flutter build web
```

<<<<<<< ours
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
=======
## หมายเหตุ

- ในเวอร์ชันปัจจุบัน รหัสผ่านของติวเตอร์ยังถูกเก็บไว้ใน Firestore โดยตรง
- ระบบแอดมินยังใช้รหัสผ่านแบบ hardcoded ภายในแอป
- รูปโปรไฟล์ปัจจุบันถูกเก็บเป็น Base64 ใน Firestore
- ข้อความภาษาไทยบางส่วนใน source code อาจยังมีปัญหาเรื่อง encoding

## แนวทางการพัฒนาต่อ

- เปลี่ยนไปใช้ `Firebase Authentication` สำหรับการเข้าสู่ระบบ
- ย้ายการเก็บรูปภาพไปใช้ `Firebase Storage`
- เพิ่มการตรวจสอบข้อมูลและการจัดการ error ให้ครอบคลุมมากขึ้น
- เพิ่ม unit test และ widget test
- ปรับปรุงระบบยืนยันตัวตนของแอดมินให้ปลอดภัยมากขึ้น

## ผู้จัดทำ

- นาย บุญยศักดิ์ รัตนดิลก ณ ภูเก็ต
- รหัสนักศึกษา 67102010165ฃ
>>>>>>> theirs
