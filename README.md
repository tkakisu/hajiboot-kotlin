# hajiboot-kotlin

『初めてのSpring Boot [改訂版]』(isbn:978-4-7775-1969-9)のkotlin + Gradle実装。

## bootstrap 4.0

https://getbootstrap.com/docs/4.0/getting-started/introduction/ のとおり修正。

- btn-default -> btn-secondaryに変更。
- form.form-horizontalは除去
  - 各行ごと（今回はdiv.form-group）に.rowを指定する。
- control-label -> col-form-label
  - https://getbootstrap.com/docs/4.0/migration/#forms-1
- col-sm-offset-2 -> offset-sm-2
- .help-block -> .form-text
- table-condensed -> table-sm
- .btn-xs -> .btn-sm
- エラー表記を変更
  - div.formGroupのth:ifは削除
  - 対象のinputに、```th:classappend="${#fields.hasErrors('firstName')}? 'is-invalid'"``` を追加
  - エラーメッセージには`invalid-feedback`を付ける

