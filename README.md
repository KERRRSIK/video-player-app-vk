# 📺 **Simple Video Player**  

### 📌 **Описание проекта**  
Simple Video Player — это Android-приложение для просмотра видео, разработанное в качестве тестового задания. Оно демонстрирует базовые навыки работы с **Android SDK**, **MVVM**, **Retrofit**, **ExoPlayer**, а также управление зависимостями через **Dagger Hilt**.  

### 🎯 **Функциональность**  

#### 1️⃣ **Экран со списком видео**  
✅ Отображение списка видео  
✅ Для каждого видео показывается **миниатюра, название и продолжительность**  
✅ Загрузка данных из **удаленного API**  
✅ Возможность обновления списка при помощи **SwipeRefreshLayout**  

#### 2️⃣ **Экран просмотра видео**  
✅ При нажатии на видео открывается **экран плеера**  
✅ Кнопки **паузы, воспроизведения, перемотки**  
✅ Поддержка **полноэкранного режима** при повороте экрана  

#### 3️⃣ **Архитектура и технологии**  
✅ Используется **MVVM**  
✅ Сетевые запросы через **Retrofit**  
✅ Управление зависимостями через **Dagger Hilt**  
✅ Минимальная поддерживаемая версия **Android 8.0 (API 26)**  

#### 4️⃣ **Дополнительные возможности**  
✅ **Кэширование списка видео** (Room)  

---  

### ❗ **Проблемы и ограничения**  
При разработке приложения возникли **сложности с выбором API** для работы с видео, так как многие популярные платформы имеют ограничения:  

| API | Проблема |
|------|----------|
| **Vimeo** | Не работает без VPN, нет прямого потокового воспроизведения |
| **Pexels** | Не работает без VPN |
| **Dailymotion** | Работает без VPN, но требует **партнёрский аккаунт** для получения потокового видео |
| **YouTube** | Полноценно не работает в России без VPN, нет возможности использовать свой плеер |
| **ВК.Видео** | Позволяет работать только с видео из **аккаунта пользователя**, парсинг без токена невозможен |
| **RuTube** | Документация недоступна без **личного обращения**, нет доступа к потоковому видео |

🔍 **Выбор API:**  
В итоге было решено использовать **Twitch API**, так как:  
✔ Оно показалось **самым удобным** и открытым  
✔ Оно не требует VPN для работы  
❌ Однако **не поддерживает прямую интеграцию с ExoPlayer**, поэтому пришлось использовать встроенные возможности Twitch  

Несмотря на ограничение с ExoPlayer, **это всё же лучше, чем ничего**. Приложение реализовано по современным стандартам и демонстрирует необходимые навыки разработки.  

---

### 🚀 **Как запустить проект**  
1. **Клонируйте репозиторий:**  
   ```bash
   git clone https://github.com/KERRRSIK/video-player-app-vk.git
   ```
2. **Откройте в Android Studio**  
3. **Соберите и запустите проект**  
4. **Готово!** 🎉  

---

### 💡 **Что можно улучшить?**  
🔹 Найти альтернативный API с поддержкой потокового видео для ExoPlayer  
🔹 Улучшить кэширование и оптимизацию загрузки видео  
🔹 Добавить тестирование (юнит-тесты и UI-тесты)  
