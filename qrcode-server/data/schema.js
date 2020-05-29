import Sequelize from "sequelize";
import { dbConfig } from "../serverconfig";

const sequelize = new Sequelize(
  dbConfig.database,
  dbConfig.user,
  dbConfig.password,
  {
    host: dbConfig.host,
    dialect: dbConfig.dialect,
    define: {
      timestamps: false,
    },
  }
);

let db = {};

db["user"] = require("./models/user")(sequelize, Sequelize);

db.sequelize = sequelize;
db.Sequelize = Sequelize;

export default db;
