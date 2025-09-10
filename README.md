# TODO API - Domain Driven Design Sample

Spring Boot と Java 17 を使用したドメイン駆動設計(DDD)に基づくTODO管理REST APIです。
（Claude Code Sonnet 4で生成）

### プロンプト

```prompt
簡単なTODOアプリのREST APIをドメイン駆動設計で作成したい。Java17+Spring Bootでお願いします。
```

## 必要要件

- Java 17
- Gradle 8.1.1

## セットアップ

### Java 17のインストール (macOS)

```bash
brew install openjdk@17
export JAVA_HOME=/opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home
```

## アプリケーションの起動

```bash
./gradlew bootRun
```

アプリケーションは `http://localhost:8080` で起動します。

## API エンドポイント

### 1. TODO作成
**POST** `/api/todos`

リクエストボディ:
```json
{
  "title": "買い物リスト作成",
  "description": "週末の買い物リストを作成する",
  "priority": "HIGH"
}
```

レスポンス例:
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "title": "買い物リスト作成",
  "description": "週末の買い物リストを作成する",
  "completed": false,
  "priority": "HIGH",
  "createdAt": "2025-09-09T15:00:00",
  "updatedAt": "2025-09-09T15:00:00"
}
```

### 2. TODO一覧取得
**GET** `/api/todos`

クエリパラメータ（オプション）:
- `completed`: `true` または `false` - 完了/未完了でフィルタリング

レスポンス例:
```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "title": "買い物リスト作成",
    "description": "週末の買い物リストを作成する",
    "completed": false,
    "priority": "HIGH",
    "createdAt": "2025-09-09T15:00:00",
    "updatedAt": "2025-09-09T15:00:00"
  }
]
```

### 3. 特定のTODO取得
**GET** `/api/todos/{id}`

レスポンス例:
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "title": "買い物リスト作成",
  "description": "週末の買い物リストを作成する",
  "completed": false,
  "priority": "HIGH",
  "createdAt": "2025-09-09T15:00:00",
  "updatedAt": "2025-09-09T15:00:00"
}
```

### 4. TODO更新
**PUT** `/api/todos/{id}`

リクエストボディ（すべてのフィールドはオプション）:
```json
{
  "title": "買い物リスト作成（更新）",
  "description": "週末の買い物リストを作成する - 野菜を追加",
  "priority": "MEDIUM",
  "completed": true
}
```

### 5. TODO削除
**DELETE** `/api/todos/{id}`

レスポンス: 204 No Content

## 使用例 (curl)

### TODO作成
```bash
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{
    "title": "プロジェクト企画書作成",
    "description": "新規プロジェクトの企画書を作成する",
    "priority": "HIGH"
  }'
```

### TODO一覧取得
```bash
curl http://localhost:8080/api/todos
```

### 未完了のTODOのみ取得
```bash
curl "http://localhost:8080/api/todos?completed=false"
```

### 特定のTODO取得
```bash
curl http://localhost:8080/api/todos/{id}
```

### TODO更新（完了にする）
```bash
curl -X PUT http://localhost:8080/api/todos/{id} \
  -H "Content-Type: application/json" \
  -d '{
    "completed": true
  }'
```

### TODO削除
```bash
curl -X DELETE http://localhost:8080/api/todos/{id}
```

## Priority（優先度）の値

- `LOW` - 低
- `MEDIUM` - 中（デフォルト）
- `HIGH` - 高

## アーキテクチャ

このプロジェクトはドメイン駆動設計（DDD）の原則に従って構築されています：

### レイヤー構成

- **Domain層**: ビジネスロジックとドメインモデル
  - Entity: `Todo`
  - Value Object: `TodoId`, `Title`
  - Repository Interface: `TodoRepository`
  - Domain Service: `TodoService`

- **Application層**: アプリケーションサービスとDTO
  - Application Service: `TodoApplicationService`
  - DTO: `TodoDto`, `CreateTodoCommand`, `UpdateTodoCommand`

- **Infrastructure層**: 技術的な実装
  - Repository実装: `InMemoryTodoRepository`

- **Presentation層**: REST APIエンドポイント
  - Controller: `TodoController`

## ビルドと実行

### ビルド
```bash
./gradlew build
```

### テスト実行
```bash
./gradlew test
```

### クリーンビルド
```bash
./gradlew clean build
```