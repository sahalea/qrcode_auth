module.exports = function (sequelize, DataTypes) {
  return sequelize.define(
    "user",
    {
      userId: {
        type: DataTypes.INTEGER,
        allowNull: false,
        primaryKey: true,
        autoIncrement: true,
      },
      name: {
        type: DataTypes.STRING,
        allowNull: true,
      },
      username: {
        type: DataTypes.STRING,
        allowNull: true,
        unique: true,
      },
      password: {
        type: DataTypes.STRING,
        allowNull: true,
      },
      userKey: {
        type: DataTypes.STRING,
        allowNull: false,
      },
    },
    {
      tableName: "user",
    }
  );
};
