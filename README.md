# Lost & Found Android App

An Android application to help users post and browse adverts for lost or found items.

## 📱 Features

- Create a new advert for a **lost** or **found** item.
- View a list of all existing adverts.
- Each advert includes:
  - Type (Lost/Found)
  - Item name
  - Description
  - Contact information
  - Date and Location

## 🛠️ Built With

- **Java** (Android)
- **XML** for layouts
- **SQLite** or in-memory storage (based on your implementation)
- Material Design styling

## 🧭 App Flow

1. **MainActivity**: Landing screen with two buttons:
   - `Create Advert`
   - `List All Adverts`

2. **CreateAdvertActivity**:
   - Form with input fields for item details.
   - Submit button to save advert.

3. **ListAdvertsActivity**:
   - Displays a list of all lost/found adverts.
   - Each item is cleanly separated and styled.



## 🎨 Styling Notes

- Custom buttons with rounded corners (`@drawable/rounded_bg`)
- Modern, clean UI with spacing and divider styles



