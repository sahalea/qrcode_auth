import schema from "../data/schema";

const loginUser = async (req, res) => {
  const model = schema["user"];
  const data = req.body;
  if (model) {
    let payload = await model.findOne({
      where: {
        userKey: data.userKey,
      },
    });
    if (payload !== null) {
      payload = {
        name: payload.name,
        username: payload.username,
      };
      return res.status(200).json({ success: true, data: payload });
    } else {
      return res
        .status(200)
        .json({ success: false, data: "No registered user on this key" });
    }
  }
};

export default loginUser;
